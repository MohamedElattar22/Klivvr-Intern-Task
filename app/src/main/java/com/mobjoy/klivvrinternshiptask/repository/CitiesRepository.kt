package com.mobjoy.klivvrinternshiptask.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mobjoy.klivvrinternshiptask.data.CitiesResponseItem
import javax.inject.Inject

class CitiesRepository @Inject constructor(private val context: Context) {
    fun getAllCities(): List<CitiesResponseItem> {
        val jsonCitiesData = context.assets.open("cities.json")
            .bufferedReader()
            .use {
                it.readText()
            }
        val gson = Gson()
        val result = gson.fromJson(jsonCitiesData, Array<CitiesResponseItem>::class.java).toList()
        Log.d("mohamed", result.toString())
        return result
    }
}