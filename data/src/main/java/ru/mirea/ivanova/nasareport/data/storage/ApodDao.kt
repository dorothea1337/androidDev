package ru.mirea.ivanova.nasareport.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.mirea.ivanova.nasareport.domain.models.ApodItem

@Dao
interface ApodDao {
    @Insert
    suspend fun insert(apodItem: ApodItem)

    @Query("SELECT * FROM apoditem")
    suspend fun getAll(): List<ApodItem>
}