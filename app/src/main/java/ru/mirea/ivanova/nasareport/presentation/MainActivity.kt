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
import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase
import ru.mirea.ivanova.nasareport.presentation.viewmodel.ApodViewModel
import ru.mirea.ivanova.nasareport.presentation.viewmodel.ApodViewModelFactory
import ru.mirea.ivanova.nasareport.presentation.viewmodel.AsteroidPreviewViewModel
import ru.mirea.ivanova.nasareport.presentation.viewmodel.AsteroidPreviewViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ApodViewModel by viewModels {
        val repo = NasaRepositoryImpl(this)
        val useCase = GetApodUseCase(repo)
        ApodViewModelFactory(useCase)
    }

    private val asteroidViewModel: AsteroidPreviewViewModel by viewModels {
        val repo = NasaRepositoryImpl(this)
        val useCase = GetAsteroidsUseCase(repo)
        AsteroidPreviewViewModelFactory(useCase)
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
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, EpicListFragment())
                .addToBackStack(null)
                .commit()
        }

        asteroidViewModel.top3Asteroids.observe(this) { list ->
            binding.asteroid1.text = list.getOrNull(0)?.name ?: "-"
            binding.asteroid2.text = list.getOrNull(1)?.name ?: "-"
            binding.asteroid3.text = list.getOrNull(2)?.name ?: "-"
        }

        binding.btnAsteroids.setOnClickListener {
            val intent = Intent(this, AsteroidsActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ProfileFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
