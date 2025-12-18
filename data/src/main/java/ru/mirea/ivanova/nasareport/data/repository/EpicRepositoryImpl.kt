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
import ru.mirea.ivanova.nasareport.data.network.dto.EpicDto

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
        val entities = dao.getAllLive()
        return entities.map { list ->
            list.map { EpicMapper.entityToDomain(it) }
        }
    }

    private val networkLiveData = MutableLiveData<List<EpicImage>>()
    override fun getEpicFromNetwork(): LiveData<List<EpicImage>> = networkLiveData

    override suspend fun refreshEpic() {
        withContext(Dispatchers.IO) {
            try {

                // ✔️ MOCK DTO LIST
                val dtos = EpicMockDataSource.urls.mapIndexed { index, url ->

                    // Вырезаем имя файла из URL
                    val fileName = url.substringAfterLast("/").removeSuffix(".png")

                    EpicDto(
                        identifier = index.toString(),
                        date = "2025-05-19 00:00:00",
                        caption = "Mock EPIC image",
                        centroid_coordinates = null,
                        dscovr_j2000_position = null,
                        lunar_j2000_position = null,
                        sun_j2000_position = null,
                        attitude_quaternions = null,
                        image = fileName
                    )
                }

                val entities = dtos.map { EpicMapper.dtoToEntity(it) }
                dao.insertAll(entities)

                val domainList = dtos.map { EpicMapper.dtoToDomain(it) }
                networkLiveData.postValue(domainList)

            } catch (e: Exception) {
                e.printStackTrace()
                networkLiveData.postValue(emptyList())
            }
        }
    }

}
