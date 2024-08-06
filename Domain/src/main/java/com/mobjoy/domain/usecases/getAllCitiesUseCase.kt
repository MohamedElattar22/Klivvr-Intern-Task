package com.mobjoy.domain.usecases

import com.mobjoy.domain.model.CitiesResponseItem
import com.mobjoy.domain.repository.CitiesRepository
import javax.inject.Inject

class getAllCitiesUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {
    suspend fun invoke(): List<CitiesResponseItem> {
        return citiesRepository.getAllCitiesRepo()
    }
}