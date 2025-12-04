package ru.mirea.ivanova.nasareport.data.network

import ru.mirea.ivanova.nasareport.domain.models.ApodItem

class NetworkApi {
    fun getApod(): ApodItem {
        return ApodItem(
            title = "Mocked Galaxy",
            description = "This is a mocked photo from the network.",
            date = "2025-10-30",
            imageUrl = "https://example.com/mock.jpg"
        )
    }
}