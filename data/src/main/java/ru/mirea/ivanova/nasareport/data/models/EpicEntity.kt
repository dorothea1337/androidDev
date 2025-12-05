package ru.mirea.ivanova.nasareport.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "epic_item")
data class EpicEntity(
    @PrimaryKey val id: String,
    val date: String,
    val caption: String,
    val centroidCoordinates: String?,
    val dscovrJ2000Position: String?,
    val lunarJ2000Position: String?,
    val sunJ2000Position: String?,
    val attitudeQuaternions: String?,
    val imageUrl: String
)
