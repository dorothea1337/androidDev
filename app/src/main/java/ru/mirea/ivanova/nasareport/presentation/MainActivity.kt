package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.data.repository.NasaRepositoryImpl
import ru.mirea.ivanova.nasareport.databinding.ActivityMainBinding
import ru.mirea.ivanova.nasareport.domain.repository.NasaRepository
import ru.mirea.ivanova.nasareport.domain.usecases.GetApodUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NasaRepositoryImpl(this)
        val getApodUseCase = GetApodUseCase(repository)

        lifecycleScope.launch {
            try {
                val apod = getApodUseCase.execute()
                Log.d("NasaApp", "Title: ${apod.title}, Date: ${apod.date}")
            } catch (e: Exception) {
                Log.e("NasaApp", "Ошибка при получении APOD: ${e.message}")
            }
        }
    }
}