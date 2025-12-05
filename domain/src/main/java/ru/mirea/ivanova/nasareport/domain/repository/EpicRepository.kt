package ru.mirea.ivanova.nasareport.domain.repository

import androidx.lifecycle.LiveData
import ru.mirea.ivanova.nasareport.domain.models.EpicImage

interface EpicRepository {
    fun getEpicFromDb(): LiveData<List<EpicImage>>
    fun getEpicFromNetwork(): LiveData<List<EpicImage>>
    suspend fun refreshEpic()
}