package ru.mirea.ivanova.nasareport.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path
import ru.mirea.ivanova.nasareport.data.network.dto.ApodDto
import ru.mirea.ivanova.nasareport.data.network.dto.EpicDto
import ru.mirea.ivanova.nasareport.data.network.dto.asteroid.NeoWsResponseDto

interface NasaService {

    @GET("planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String
    ): ApodDto

    @GET("EPIC/api/natural")
    suspend fun getEpicList(
        @Query("api_key") apiKey: String
    ): List<EpicDto>

    @GET("enhanced/date/{date}")
    suspend fun getEpicByDate(
        @Path("date") date: String
    ): List<EpicDto>

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): NeoWsResponseDto

}
