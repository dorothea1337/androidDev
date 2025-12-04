package ru.mirea.ivanova.nasareport.data.network

class NetworkApi {
    // Для учебного мок-апа — синхронный. При желании можно сделать suspend и retrofit.
    fun getApod(): ApodDto {
        return ApodDto(
            date = "2025-10-30",
            title = "Mocked Galaxy (DTO)",
            explanation = "This is a mocked photo from the network (DTO).",
            url = "https://example.com/mock.jpg"
        )
    }
}
