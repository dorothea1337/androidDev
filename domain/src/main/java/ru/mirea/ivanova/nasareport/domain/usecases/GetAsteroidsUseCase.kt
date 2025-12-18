package ru.mirea.ivanova.nasareport.domain.usecases

import androidx.lifecycle.LiveData
import ru.mirea.ivanova.nasareport.domain.models.Asteroid
import ru.mirea.ivanova.nasareport.domain.repository.NasaRepository

class GetAsteroidsUseCase(
    private val repository: NasaRepository
) {
    fun observeAsteroids(): LiveData<List<Asteroid>> =
        repository.getAsteroids()

    suspend fun refresh() {
        repository.refreshAsteroids()
    }
}
