package ru.mirea.ivanova.nasareport.data.mappers

import ru.mirea.ivanova.nasareport.data.network.dto.asteroid.NearEarthObjectDto
import ru.mirea.ivanova.nasareport.domain.models.Asteroid

object AsteroidMapper {

    fun dtoToDomain(dto: NearEarthObjectDto): Asteroid {
        val approach = dto.closeApproachData.firstOrNull()
        val speed = approach?.relativeVelocity?.kmPerHour?.toDoubleOrNull() ?: 0.0
        val distance = approach?.missDistance?.kilometers?.toDoubleOrNull() ?: 0.0

        return Asteroid(
            name = dto.name,
            diameter = dto.estimatedDiameter.kilometers.max,
            isDangerous = dto.isPotentiallyHazardous,
            distance = distance,
            speed = speed
        )
    }

    fun mapNeoWsResponse(response: ru.mirea.ivanova.nasareport.data.network.dto.asteroid.NeoWsResponseDto): List<Asteroid> {
        val list = mutableListOf<Asteroid>()
        response.nearEarthObjects.forEach { (_, asteroids) ->
            asteroids.forEach { dto ->
                list.add(dtoToDomain(dto))
            }
        }
        return list
    }
}