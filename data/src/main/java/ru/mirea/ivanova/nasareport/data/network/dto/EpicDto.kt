package ru.mirea.ivanova.nasareport.data.network.dto

data class EpicDto(
    val identifier: String,
    val date: String,
    val caption: String,
    val centroid_coordinates: Map<String, Double>?,
    val dscovr_j2000_position: Map<String, Double>?,
    val lunar_j2000_position: Map<String, Double>?,
    val sun_j2000_position: Map<String, Double>?,
    val attitude_quaternions: Map<String, Double>?,
    val image: String
)