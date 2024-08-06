package com.mobjoy.data.repositoryimpl

import com.mobjoy.data.datasources.CitiesDataSource
import com.mobjoy.domain.model.CitiesResponseItem
import com.mobjoy.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(private val citiesDataSource: CitiesDataSource) :
    CitiesRepository {
    override suspend fun getAllCitiesRepo(): List<CitiesResponseItem> {
        return citiesDataSource.getAllCitiesRepo()
    }


}