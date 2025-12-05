package ru.mirea.ivanova.nasareport.domain.models

data class EpicImage(
    val id: String, // уникальное имя изображения (name from API)
    val date: String,
    val caption: String,
    val centroidCoordinates: String?, // можно парсить в объект позже
    val dscovrJ2000Position: String?,
    val lunarJ2000Position: String?,
    val sunJ2000Position: String?,
    val attitudeQuaternions: String?,
    val imageUrl: String // ссылка на картинку (сформируем по правилам API или укажем тестовую)
)