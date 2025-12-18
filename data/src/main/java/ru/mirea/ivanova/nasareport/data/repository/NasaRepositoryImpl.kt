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
import ru.mirea.ivanova.nasareport.domain.models.Asteroid
import java.text.SimpleDateFormat
import java.util.*
import ru.mirea.ivanova.nasareport.data.mappers.AsteroidMapper

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

    private val asteroidsLiveData = MutableLiveData<List<Asteroid>>()

    override fun getAsteroids(): LiveData<List<Asteroid>> = asteroidsLiveData

    override suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

                val calendar = Calendar.getInstance()
                val today = calendar.time

                calendar.add(Calendar.DAY_OF_MONTH, -1)
                val yesterday = calendar.time

                val startDate = sdf.format(yesterday)
                val endDate = sdf.format(today)

                val response = api.getAsteroids(startDate, endDate)
                val list = AsteroidMapper.mapNeoWsResponse(response)

                asteroidsLiveData.postValue(list)
            } catch (e: Exception) {
                e.printStackTrace()
                asteroidsLiveData.postValue(emptyList())
            }
        }
    }


}
