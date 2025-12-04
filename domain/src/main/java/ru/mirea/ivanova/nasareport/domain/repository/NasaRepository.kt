package ru.mirea.ivanova.nasareport.domain.repository

import ru.mirea.ivanova.nasareport.domain.models.ApodItem

interface NasaRepository {
    suspend fun getApod(): ApodItem {
        return ApodItem(
            title = "Amazing Galaxy",
            description = "A beautiful photo of a distant galaxy captured by Hubble.",
            date = "2025-10-30",
            imageUrl = "https://example.com/galaxy.jpg"
        )
    }
}