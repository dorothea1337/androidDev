package ru.mirea.ivanova.nasareport.domain.repository

import androidx.lifecycle.LiveData
import ru.mirea.ivanova.nasareport.domain.models.Apod

interface NasaRepository {
    fun getApodFromDb(): LiveData<List<Apod>>

    // Сеть (последний загруженный элемент)
    fun getApodFromNetwork(): LiveData<Apod?>

    // Обновить (загрузить из сети и сохранить в БД)
    suspend fun refreshApod()
}