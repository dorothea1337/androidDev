package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ru.mirea.ivanova.nasareport.databinding.ActivityEpicBinding
import ru.mirea.ivanova.nasareport.data.repository.EpicRepositoryImpl
import ru.mirea.ivanova.nasareport.domain.usecases.GetEpicUseCase
import ru.mirea.ivanova.nasareport.presentation.adapter.EpicAdapter
import ru.mirea.ivanova.nasareport.presentation.viewmodel.EpicViewModel
import ru.mirea.ivanova.nasareport.presentation.viewmodel.EpicViewModelFactory

class EpicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEpicBinding

    private val viewModel: EpicViewModel by viewModels {
        val repo = EpicRepositoryImpl(this)
        val useCase = GetEpicUseCase(repo)
        EpicViewModelFactory(useCase)
    }

    private val adapter = EpicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Earth pictures"

        binding.recyclerViewEpic.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEpic.adapter = adapter

        viewModel.epicList.observe(this) { list ->
            adapter.setItems(list)
        }

        viewModel.refresh()
    }
}
