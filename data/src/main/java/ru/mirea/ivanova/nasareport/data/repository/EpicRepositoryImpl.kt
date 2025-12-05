package ru.mirea.ivanova.nasareport.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mirea.ivanova.nasareport.data.mappers.EpicMapper
import ru.mirea.ivanova.nasareport.data.network.NetworkApi
import ru.mirea.ivanova.nasareport.data.storage.AppDatabase
import ru.mirea.ivanova.nasareport.data.storage.SharedPrefsHelper
import ru.mirea.ivanova.nasareport.domain.models.EpicImage
import ru.mirea.ivanova.nasareport.domain.repository.EpicRepository

class EpicRepositoryImpl(context: Context) : EpicRepository {
    private val prefs = SharedPrefsHelper(context)
    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "nasa.db"
    ).build()
    private val dao = db.epicDao()
    private val api = NetworkApi()

    override fun getEpicFromDb(): LiveData<List<EpicImage>> {
        val entitiesLive = dao.getAllLive()
        return entitiesLive.map { list ->
            list.map { EpicMapper.entityToDomain(it) }
        }
    }

    private val networkLiveData = MutableLiveData<List<EpicImage>>()
    override fun getEpicFromNetwork(): LiveData<List<EpicImage>> = networkLiveData

    override suspend fun refreshEpic() {
        withContext(Dispatchers.IO) {
            // get mock list from network API
            val dtos = api.getEpicList()
            val entities = dtos.map { EpicMapper.dtoToEntity(it) }
            dao.insertAll(entities)

            val domainList = dtos.map { EpicMapper.dtoToDomain(it) }
            networkLiveData.postValue(domainList)

            // optionally store something in prefs
            prefs.saveUserEmail("epic@example.com")
        }
    }
}
