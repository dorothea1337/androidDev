package ru.mirea.ivanova.nasareport.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.ivanova.nasareport.data.models.ApodItem

@Database(entities = [ApodItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}
