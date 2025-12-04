package ru.mirea.ivanova.nasareport.data.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.mirea.ivanova.nasareport.data.models.ApodItem

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apodItem: ApodItem)

    // LiveData для наблюдения
    @Query("SELECT * FROM apoditem ORDER BY date DESC")
    fun getAllLive(): LiveData<List<ApodItem>>

    @Query("SELECT * FROM apoditem ORDER BY date DESC")
    suspend fun getAll(): List<ApodItem>
}
