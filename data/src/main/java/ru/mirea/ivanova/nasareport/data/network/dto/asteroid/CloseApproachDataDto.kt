package ru.mirea.ivanova.nasareport.data.network.dto.asteroid

import com.google.gson.annotations.SerializedName

data class CloseApproachDataDto(
    @SerializedName("relative_velocity")
    val relativeVelocity: RelativeVelocityDto,

    @SerializedName("miss_distance")
    val missDistance: MissDistanceDto
)
