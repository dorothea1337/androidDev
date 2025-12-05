package ru.mirea.ivanova.nasareport.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mirea.ivanova.nasareport.data.models.ApodItem
import ru.mirea.ivanova.nasareport.data.models.EpicEntity

@Database(entities = [ApodItem::class, EpicEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apodDao(): ApodDao
    abstract fun epicDao(): EpicDao
}
