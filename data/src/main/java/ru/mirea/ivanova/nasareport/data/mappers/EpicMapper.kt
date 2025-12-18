package ru.mirea.ivanova.nasareport.data.mappers

import com.google.gson.Gson
import android.util.Log
import ru.mirea.ivanova.nasareport.data.models.EpicEntity
import ru.mirea.ivanova.nasareport.data.network.dto.EpicDto
import ru.mirea.ivanova.nasareport.domain.models.EpicImage
object EpicMapper {

    private val gson = Gson()

    fun buildEpicImageUrl(dto: EpicDto): String {
        val datePath = dto.date.substring(0, 10).replace("-", "/")
        val url = "https://epic.gsfc.nasa.gov/archive/natural/$datePath/png/${dto.image}.png"
        Log.d("EPIC_URL", "image=${dto.image} | url=$url")
        return url
    }

    fun dtoToEntity(dto: EpicDto): EpicEntity {
        return EpicEntity(
            id = dto.identifier,
            date = dto.date,
            caption = dto.caption,
            centroidCoordinates = dto.centroid_coordinates?.let { gson.toJson(it) },
            dscovrJ2000Position = dto.dscovr_j2000_position?.let { gson.toJson(it) },
            lunarJ2000Position = dto.lunar_j2000_position?.let { gson.toJson(it) },
            sunJ2000Position = dto.sun_j2000_position?.let { gson.toJson(it) },
            attitudeQuaternions = dto.attitude_quaternions?.let { gson.toJson(it) },
            imageUrl = buildEpicImageUrl(dto)
        )
    }

    fun entityToDomain(entity: EpicEntity): EpicImage {
        return EpicImage(
            id = entity.id,
            date = entity.date,
            caption = entity.caption,
            centroidCoordinates = entity.centroidCoordinates,
            dscovrJ2000Position = entity.dscovrJ2000Position,
            lunarJ2000Position = entity.lunarJ2000Position,
            sunJ2000Position = entity.sunJ2000Position,
            attitudeQuaternions = entity.attitudeQuaternions,
            imageUrl = entity.imageUrl
        )
    }

    fun dtoToDomain(dto: EpicDto): EpicImage {
        return entityToDomain(dtoToEntity(dto))
    }
}

