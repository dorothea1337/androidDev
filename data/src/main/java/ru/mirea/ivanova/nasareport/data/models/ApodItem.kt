package ru.mirea.ivanova.nasareport.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ApodItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)