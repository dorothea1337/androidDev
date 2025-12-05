package ru.mirea.ivanova.nasareport.domain.models

data class Apod(
    val date: String,
    val title: String,
    val description: String,
    val imageUrl: String
)