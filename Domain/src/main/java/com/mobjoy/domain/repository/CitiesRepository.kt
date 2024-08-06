package com.mobjoy.domain.repository

import com.mobjoy.domain.model.CitiesResponseItem

interface CitiesRepository {
    suspend fun getAllCitiesRepo(): List<CitiesResponseItem>
}