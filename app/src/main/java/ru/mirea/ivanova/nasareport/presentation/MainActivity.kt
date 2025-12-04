package ru.mirea.ivanova.nasareport.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
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

        // Тестовый блок для проверки clean architecture и MediatorLiveData
        /*fun testMediatorLiveData() {
            Log.d("Test", "=== START TEST MediatorLiveData ===")

            // 1. Проверка подписки через ViewModel
            viewModel.mergedData.observe(this) { list ->
                Log.d("Test", "MergedData size: ${list.size}")
                list.forEach { apod ->
                    Log.d("Test", "APOD: ${apod.date} - ${apod.title}")
                }
            }

            // 2. Проверка LiveData обновления
            Log.d("Test", "Triggering refresh to simulate network update...")
            viewModel.refresh()  // запускаем сетевое обновление

            // 3. Проверка объединения данных (MediatorLiveData)
            // Имитируем добавление нового элемента в БД напрямую через репозиторий
            val repo = NasaRepositoryImpl(this)
            lifecycleScope.launch {
                // Создаём тестовый объект
                val testApod = ru.mirea.ivanova.nasareport.data.network.ApodDto(
                    date = "2025-12-05",
                    title = "Test Galaxy",
                    explanation = "This is a test entry for DB.",
                    url = "https://example.com/test.jpg"
                )
                // Сохраняем в БД через DAO напрямую
                repo.refreshApod()  // Это уже сохраняет сеть в БД и обновляет LiveData
                Log.d("Test", "DB + Network should now be merged in mergedData.")
            }
        }*/


        // Подписываемся на объединённые данные (MediatorLiveData)
        viewModel.mergedData.observe(this) { list ->
            Log.d("NasaApp", "Merged APOD items: ${list.size}")
            list.forEach { apod ->
                Log.d("NasaApp", "${apod.date} - ${apod.title}")
            }
        }

        // Запускаем обновление из сети
        viewModel.refresh()

        //testMediatorLiveData()
    }
}
