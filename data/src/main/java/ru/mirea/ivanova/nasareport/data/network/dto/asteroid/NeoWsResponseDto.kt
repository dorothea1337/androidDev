package ru.mirea.ivanova.nasareport.data.network.dto.asteroid

import com.google.gson.annotations.SerializedName

data class NeoWsResponseDto(
    @SerializedName("near_earth_objects")
    val nearEarthObjects: Map<String, List<NearEarthObjectDto>>
)