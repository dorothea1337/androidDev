package ru.mirea.ivanova.nasareport.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.ivanova.nasareport.data.mappers.ApodMapper
import ru.mirea.ivanova.nasareport.data.network.NetworkApi
import ru.mirea.ivanova.nasareport.data.storage.AppDatabase
import ru.mirea.ivanova.nasareport.data.storage.SharedPrefsHelper
import ru.mirea.ivanova.nasareport.domain.models.Apod
import ru.mirea.ivanova.nasareport.domain.repository.NasaRepository

class NasaRepositoryImpl(context: Context) : NasaRepository {
    private val prefs = SharedPrefsHelper(context)

    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "nasa.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    private val dao = db.apodDao()
    private val api = NetworkApi()

    // LiveData сущностей из DAO -> преобразуем в доменные модели
    override fun getApodFromDb(): LiveData<List<Apod>> {
        val entitiesLive = dao.getAllLive()
        return entitiesLive.map { list ->
            list.map { ApodMapper.entityToDomain(it) }
        }
    }

    // LiveData для последней загруженной записи (domain)
    private val networkLiveData = MutableLiveData<Apod?>()
    override fun getApodFromNetwork(): LiveData<Apod?> = networkLiveData

    override suspend fun refreshApod() {
        withContext(Dispatchers.IO) {
            try {
                val dto = api.getApod() // реальный сетевой вызов
                val entity = ApodMapper.dtoToEntity(dto)
                dao.insert(entity)
                val domain = ApodMapper.dtoToDomain(dto)
                networkLiveData.postValue(domain)
            } catch (e: Exception) {
                e.printStackTrace()
                networkLiveData.postValue(null) // сеть упала
            }
        }
    }
}
