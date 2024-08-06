package com.mobjoy.domain

data class CitiesResponseItem(
    val country: String? = null,
    val coord: Coord? = null,
    val name: String? = null,
    val id: Int? = null
) {
    fun toCitiesResponseItemDto(): CitiesResponseItem {
        return CitiesResponseItem(
            country = country,
            coord = coord,
            name = name,
            id = id
        )
    }
}
