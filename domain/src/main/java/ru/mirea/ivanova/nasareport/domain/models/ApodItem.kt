package ru.mirea.ivanova.nasareport.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apoditem")
data class ApodItem(
    @PrimaryKey val date: String,
    val title: String,
    val description: String,
    val imageUrl: String
)