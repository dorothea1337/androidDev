package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import android.content.Intent
import com.squareup.picasso.Picasso

import androidx.appcompat.app.AppCompatActivity
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.databinding.ActivityMainBinding
import ru.mirea.ivanova.nasareport.data.repository.NasaRepositoryImpl
import ru.mirea.ivanova.nasareport.domain.usecases.GetApodUseCase
import ru.mirea.ivanova.nasareport.presentation.viewmodel.ApodViewModel
import ru.mirea.ivanova.nasareport.presentation.viewmodel.ApodViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ApodViewModel by viewModels {
        val repo = NasaRepositoryImpl(this)
        val useCase = GetApodUseCase(repo)
        ApodViewModelFactory(useCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.mergedData.observe(this) { list ->
            if (list.isNotEmpty()) {
                val apod = list[0]
                Picasso.get()
                    .load(apod.imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(binding.apodImage)
            }
        }


        viewModel.refresh()

        binding.btnEpic.setOnClickListener {
            val intent = Intent(this, EpicActivity::class.java)
            startActivity(intent)
        }
    }
}
