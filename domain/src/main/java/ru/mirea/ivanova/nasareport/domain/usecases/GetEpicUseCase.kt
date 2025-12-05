package ru.mirea.ivanova.nasareport.domain.usecases

import androidx.lifecycle.LiveData
import ru.mirea.ivanova.nasareport.domain.models.EpicImage
import ru.mirea.ivanova.nasareport.domain.repository.EpicRepository

class GetEpicUseCase(private val repository: EpicRepository) {
    fun observeEpicList(): LiveData<List<EpicImage>> = repository.getEpicFromDb()
    fun observeNetworkEpic(): LiveData<List<EpicImage>> = repository.getEpicFromNetwork()
    suspend fun refresh() = repository.refreshEpic()
}
