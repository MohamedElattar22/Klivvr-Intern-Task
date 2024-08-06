package com.mobjoy.klivvrinternshiptask

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobjoy.klivvrinternshiptask.data.CitiesResponseItem
import com.mobjoy.klivvrinternshiptask.repository.CitiesRepository
import com.mobjoy.klivvrinternshiptask.searching.Trie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val citiesRepository: CitiesRepository
) : ViewModel() {
    val trie = Trie()
    private val cityMap: MutableMap<String, CitiesResponseItem> = mutableMapOf()
    val citiesList = MutableStateFlow(listOf(CitiesResponseItem()))
    var flag: Boolean = false
    val isLoading = MutableLiveData(true)


    fun getAllCities() {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.postValue(false)
                var result = citiesRepository.getAllCities()
                result = result.sortedBy { city ->
                    city.name
                }
                result.forEach { city ->

                    cityMap[city.name.toString()] = city
                }
                insertDataToTrie(result)
                citiesList.emit(result)

            } catch (e: Exception) {
                isLoading.postValue(false)
                Log.d("errorLog1", e.message.toString())
            }
        }
    }

    private fun insertDataToTrie(searchList: List<CitiesResponseItem?>?) {
        if (!flag) {
            searchList?.forEach {
                trie.insert(it?.name.toString())
            }
            flag = true
        }
    }

    fun getDataFromTrie(query: String): List<String> {
        return trie.getWordsWithPrefix(query)
    }

    fun getCityMap() = cityMap

}