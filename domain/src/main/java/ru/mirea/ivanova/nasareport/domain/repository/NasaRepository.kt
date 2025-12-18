package ru.mirea.ivanova.nasareport.domain.repository

import androidx.lifecycle.LiveData
import ru.mirea.ivanova.nasareport.domain.models.Apod
import ru.mirea.ivanova.nasareport.domain.models.Asteroid

interface NasaRepository {
    fun getApodFromDb(): LiveData<List<Apod>>
    fun getApodFromNetwork(): LiveData<Apod?>
    suspend fun refreshApod()

    fun getAsteroids(): LiveData<List<Asteroid>>
    suspend fun refreshAsteroids()

}