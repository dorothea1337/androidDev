package ru.mirea.ivanova.nasareport.data.network.dto.asteroid

import com.google.gson.annotations.SerializedName

data class RelativeVelocityDto(
    @SerializedName("kilometers_per_hour")
    val kmPerHour: String
)
