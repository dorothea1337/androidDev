package ru.mirea.ivanova.nasareport.domain.usecases

import androidx.lifecycle.LiveData
import ru.mirea.ivanova.nasareport.domain.models.Apod
import ru.mirea.ivanova.nasareport.domain.repository.NasaRepository

class GetApodUseCase(private val repository: NasaRepository) {
    fun observeApodList(): LiveData<List<Apod>> = repository.getApodFromDb()
    fun observeNetworkApod(): LiveData<Apod?> = repository.getApodFromNetwork()

    suspend fun refresh() {
        repository.refreshApod()
    }
}