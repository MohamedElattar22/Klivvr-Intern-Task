package com.mobjoy.data.datasourceimpl

import android.util.Log
import com.google.gson.Gson
import com.mobjoy.data.datasources.CitiesDataSource
import com.mobjoy.domain.model.CitiesResponseItem
import java.io.BufferedReader
import javax.inject.Inject

class CitiesDataSourceImpl @Inject constructor(
    private val gson: Gson,
    private val bufferedReader: BufferedReader,

    ) : CitiesDataSource {
    override suspend fun getAllCitiesRepo(): List<CitiesResponseItem> {
        val jsonCitiesData = bufferedReader.use {
            it.readText()
        }
        val result = gson.fromJson(jsonCitiesData, Array<CitiesResponseItem>::class.java).toList()
        val cities = result.sortedBy { city ->
            city.name
        }
        Log.d("mohamed", result.toString())
        return cities
    }


}