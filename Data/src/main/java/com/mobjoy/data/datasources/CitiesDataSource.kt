package com.mobjoy.data.datasources

import com.mobjoy.domain.CitiesResponse

interface CitiesDataSource {
    suspend fun getAllCitiesRepo(): CitiesResponse
}