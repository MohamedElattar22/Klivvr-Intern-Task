package com.mobjoy.domain.repository

import com.mobjoy.domain.CitiesResponse

interface CitiesRepository {
    suspend fun getAllCitiesRepo(): CitiesResponse
}