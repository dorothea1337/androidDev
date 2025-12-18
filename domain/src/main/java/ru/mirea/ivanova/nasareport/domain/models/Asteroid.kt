package ru.mirea.ivanova.nasareport.domain.models

data class Asteroid(
    val name: String,
    val diameter: Double,
    val isDangerous: Boolean,
    val distance: Double,
    val speed: Double
)