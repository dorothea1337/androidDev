package ru.mirea.ivanova.nasareport.data.network.dto.asteroid

import com.google.gson.annotations.SerializedName

data class DiameterKilometersDto(
    @SerializedName("estimated_diameter_max")
    val max: Double
)