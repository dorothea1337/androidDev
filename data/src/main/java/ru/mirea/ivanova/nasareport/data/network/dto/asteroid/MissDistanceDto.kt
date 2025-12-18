package ru.mirea.ivanova.nasareport.data.network.dto.asteroid

import com.google.gson.annotations.SerializedName

data class MissDistanceDto(
    @SerializedName("kilometers")
    val kilometers: String
)