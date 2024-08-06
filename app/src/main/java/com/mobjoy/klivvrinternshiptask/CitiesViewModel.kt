package com.mobjoy.klivvrinternshiptask

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobjoy.klivvrinternshiptask.data.CitiesResponseItem
import com.mobjoy.klivvrinternshiptask.repository.CitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) : ViewModel() {

    val citiesList = MutableStateFlow(listOf(CitiesResponseItem()))

    val isLoading = MutableLiveData(true)

    fun getAllCities() {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.postValue(false)
                val result = citiesRepository.getAllCities()
                citiesList.emit(result)

            } catch (e: Exception) {
                isLoading.postValue(false)
                Log.d("errorLog1", e.message.toString())
            }
        }
    }


}