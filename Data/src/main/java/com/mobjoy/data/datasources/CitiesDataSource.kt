package com.mobjoy.data.datasources

import com.mobjoy.domain.model.CitiesResponseItem

interface CitiesDataSource {
    suspend fun getAllCitiesRepo(): List<CitiesResponseItem>
}