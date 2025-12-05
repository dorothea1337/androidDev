package ru.mirea.ivanova.nasareport.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

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

}
