package ru.mirea.ivanova.nasareport.data.repository

import android.content.Context
import androidx.room.Room
import ru.mirea.ivanova.nasareport.data.network.NetworkApi
import ru.mirea.ivanova.nasareport.data.storage.AppDatabase
import ru.mirea.ivanova.nasareport.data.storage.SharedPrefsHelper
import ru.mirea.ivanova.nasareport.domain.models.ApodItem
import ru.mirea.ivanova.nasareport.domain.repository.NasaRepository

class NasaRepositoryImpl(context: Context) : NasaRepository {
    private val prefs = SharedPrefsHelper(context)

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "nasa.db").build()
    private val api = NetworkApi()

    override suspend fun getApod(): ApodItem {
        val networkData = api.getApod()
        prefs.saveUserEmail("test@example.com")

        db.apodDao().insert(networkData)

        return networkData
    }
}