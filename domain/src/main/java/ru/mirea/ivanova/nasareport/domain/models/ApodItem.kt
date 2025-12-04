package ru.mirea.ivanova.nasareport.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Apod(
    val date: String,
    val title: String,
    val description: String,
    val imageUrl: String
)