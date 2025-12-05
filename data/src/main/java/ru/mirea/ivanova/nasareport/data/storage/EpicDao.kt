package ru.mirea.ivanova.nasareport.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mirea.ivanova.nasareport.data.models.EpicEntity

@Dao
interface EpicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<EpicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: EpicEntity)

    @Query("SELECT * FROM epic_item ORDER BY date DESC")
    fun getAllLive(): LiveData<List<EpicEntity>>

    @Query("SELECT * FROM epic_item ORDER BY date DESC")
    suspend fun getAll(): List<EpicEntity>
}
