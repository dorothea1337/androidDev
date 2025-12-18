package ru.mirea.ivanova.nasareport.data.network.dto.asteroid

import com.google.gson.annotations.SerializedName
data class NearEarthObjectDto(
    val name: String,

    @SerializedName("estimated_diameter")
    val estimatedDiameter: EstimatedDiameterDto,

    @SerializedName("is_potentially_hazardous_asteroid")
    val isPotentiallyHazardous: Boolean,

    @SerializedName("close_approach_data")
    val closeApproachData: List<CloseApproachDataDto>
)
