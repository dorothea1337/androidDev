package ru.mirea.ivanova.nasareport.data.mappers

import ru.mirea.ivanova.nasareport.data.models.ApodItem
import ru.mirea.ivanova.nasareport.data.network.dto.ApodDto
import ru.mirea.ivanova.nasareport.domain.models.Apod

object ApodMapper {

    // DTO -> Domain
    fun dtoToDomain(dto: ApodDto): Apod {
        return Apod(
            date = dto.date,
            title = dto.title,
            description = dto.explanation,
            imageUrl = dto.url
        )
    }

    // DTO -> Entity (id оставляем по умолчанию = 0, Room присвоит)
    fun dtoToEntity(dto: ApodDto): ApodItem {
        return ApodItem(
            id = 0,
            date = dto.date,
            title = dto.title,
            description = dto.explanation,
            imageUrl = dto.url
        )
    }

    // Entity -> Domain (игнорируем id в доменной модели)
    fun entityToDomain(entity: ApodItem): Apod {
        return Apod(
            date = entity.date,
            title = entity.title,
            description = entity.description,
            imageUrl = entity.imageUrl
        )
    }

    // Domain -> Entity (для вставки из domain если понадобится)
    fun domainToEntity(domain: Apod): ApodItem {
        return ApodItem(
            id = 0,
            date = domain.date,
            title = domain.title,
            description = domain.description,
            imageUrl = domain.imageUrl
        )
    }
}
