package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.mirea.ivanova.nasareport.R
import ru.mirea.ivanova.nasareport.databinding.ActivityAsteroidsBinding
import ru.mirea.ivanova.nasareport.presentation.adapter.AsteroidAdapter
import ru.mirea.ivanova.nasareport.presentation.viewmodel.AsteroidsViewModel
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mirea.ivanova.nasareport.data.repository.NasaRepositoryImpl
import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase
import ru.mirea.ivanova.nasareport.presentation.viewmodel.AsteroidsViewModelFactory

class AsteroidsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAsteroidsBinding
    private lateinit var adapter: AsteroidAdapter

    private val viewModel: AsteroidsViewModel by viewModels {
        val repo = NasaRepositoryImpl(this)
        val useCase = GetAsteroidsUseCase(repo)
        AsteroidsViewModelFactory(useCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsteroidsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = AsteroidAdapter(emptyList())
        binding.rvAsteroids.layoutManager = LinearLayoutManager(this)
        binding.rvAsteroids.adapter = adapter

        viewModel.asteroids.observe(this) { list ->
            adapter.setData(list)
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // возвращаемся на предыдущий экран
        }

    }
}