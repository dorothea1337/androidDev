package ru.mirea.ivanova.nasareport.data.mappers

import com.google.gson.Gson
import ru.mirea.ivanova.nasareport.data.models.EpicEntity
import ru.mirea.ivanova.nasareport.data.network.EpicDto
import ru.mirea.ivanova.nasareport.domain.models.EpicImage

object EpicMapper {

    private val gson = Gson()

    fun dtoToEntity(dto: EpicDto): EpicEntity {
        // stringify maps to simple JSON for storage
        val centroidStr = dto.centroid_coordinates?.let { gson.toJson(it) }
        val dscovrStr = dto.dscovr_j2000_position?.let { gson.toJson(it) }
        val lunarStr = dto.lunar_j2000_position?.let { gson.toJson(it) }
        val sunStr = dto.sun_j2000_position?.let { gson.toJson(it) }
        val attitudeStr = dto.attitude_quaternions?.let { gson.toJson(it) }

        // For mock we can directly create image URL from image field or use full URL
        val imageUrl = if (dto.image.startsWith("http")) dto.image
        else "https://epic.gsfc.nasa.gov/archive/natural/${dto.date.replace("-", "/")}/png/${dto.image}.png"

        return EpicEntity(
            id = dto.identifier,
            date = dto.date,
            caption = dto.caption,
            centroidCoordinates = centroidStr,
            dscovrJ2000Position = dscovrStr,
            lunarJ2000Position = lunarStr,
            sunJ2000Position = sunStr,
            attitudeQuaternions = attitudeStr,
            imageUrl = imageUrl
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

    fun dtoToDomain(dto: EpicDto): EpicImage = entityToDomain(dtoToEntity(dto))
}
