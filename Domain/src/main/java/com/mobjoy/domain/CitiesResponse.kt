package com.mobjoy.domain

data class CitiesResponse(
    val citiesResponse: List<CitiesResponseItem?>? = null
) {
    fun toCitiesResponseDto(): CitiesResponse {
        return CitiesResponse(
            citiesResponse?.map {
                it?.toCitiesResponseItemDto()
            }
        )
    }
}
