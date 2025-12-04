package ru.mirea.ivanova.nasareport.domain.usecases

import ru.mirea.ivanova.nasareport.domain.repository.NasaRepository
import ru.mirea.ivanova.nasareport.domain.models.ApodItem

class GetApodUseCase(private val repository: NasaRepository) {
    suspend fun execute(): ApodItem {
        return repository.getApod()
    }
}