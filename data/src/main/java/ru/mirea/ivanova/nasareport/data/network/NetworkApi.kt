package ru.mirea.ivanova.nasareport.data.network

class NetworkApi {
    // Для учебного мок-апа — синхронный. При желании можно сделать suspend и retrofit.
    fun getApod(): ApodDto {
        return ApodDto(
            date = "2025-10-30",
            title = "Mocked Galaxy (DTO)",
            explanation = "This is a mocked photo from the network (DTO).",
            url = "https://example.com/mock.jpg"
        )
    }

    // Mock EPIC list
    fun getEpicList(): List<EpicDto> {
        val sample1 = EpicDto(
            identifier = "epic_1",
            date = "2025-12-01",
            caption = "Earth from DSCOVR — sample 1",
            centroid_coordinates = mapOf("lat" to 0.0, "lon" to 0.0),
            dscovr_j2000_position = null,
            lunar_j2000_position = null,
            sun_j2000_position = null,
            attitude_quaternions = null,
            image = "epic_1"
        )
        val sample2 = EpicDto(
            identifier = "epic_2",
            date = "2025-12-02",
            caption = "Earth from DSCOVR — sample 2",
            centroid_coordinates = mapOf("lat" to 10.0, "lon" to 20.0),
            dscovr_j2000_position = null,
            lunar_j2000_position = null,
            sun_j2000_position = null,
            attitude_quaternions = null,
            image = "epic_2"
        )
        // For mock we can set image to a full URL for quick testing, or rely on mapper to build URL
        return listOf(sample2, sample1)
    }
}
