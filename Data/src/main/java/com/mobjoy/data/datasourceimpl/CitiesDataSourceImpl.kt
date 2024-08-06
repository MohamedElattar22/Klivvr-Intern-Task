package com.mobjoy.data.datasourceimpl

import android.content.Context
import com.google.gson.Gson
import com.mobjoy.data.datasources.CitiesDataSource
import com.mobjoy.data.model.CitiesResponseDto
import com.mobjoy.domain.CitiesResponse
import javax.inject.Inject

class CitiesDataSourceImpl @Inject constructor(private val context: Context) : CitiesDataSource {
    override suspend fun getAllCitiesRepo(): CitiesResponse {
        val jsonData = context.assets.open("cities.json")
            .bufferedReader().use {
                it.readText()
            }
        val gson = Gson()
        return gson.fromJson(jsonData, CitiesResponse::class.java)
    }

}