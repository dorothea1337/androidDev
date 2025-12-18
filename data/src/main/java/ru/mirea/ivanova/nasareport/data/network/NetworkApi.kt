package ru.mirea.ivanova.nasareport.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mirea.ivanova.nasareport.data.network.dto.ApodDto
import ru.mirea.ivanova.nasareport.data.network.dto.EpicDto
import ru.mirea.ivanova.nasareport.data.network.dto.asteroid.NeoWsResponseDto

class NetworkApi {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nasa.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(NasaService::class.java)
    private val apiKey = "ciEmSpNBF3bdL91ciiIixCEzE7ghzTMPXETAMCdY"

    suspend fun getApod(): ApodDto = service.getApod(apiKey)

    suspend fun getEpicList(): List<EpicDto> = service.getEpicList(apiKey)

    suspend fun getEpicFixedDate(): List<EpicDto> {
        return service.getEpicByDate("2025-05-19")
    }

    suspend fun getAsteroids(startDate: String, endDate: String): NeoWsResponseDto {
        return service.getAsteroids(startDate, endDate, apiKey)
    }

}
