package com.mobjoy.klivvrinternshiptask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mobjoy.klivvrinternshiptask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: CitiesViewModel
    private var citiesAdapter = CitiesAdapter(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewModel = ViewModelProvider(this)[CitiesViewModel::class.java]
        utilizingViewModel()
        settingRvAdapter()
    }

    private fun utilizingViewModel() {
        viewModel.getAllCities()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.citiesList.collect { data ->

                val sortedCities = data.sortedBy { city ->
                    city.name
                }

                if (data[0].name != null) {
                    handleLoadingContent()
                    citiesAdapter.bindCitiesList(sortedCities)
                }

                Log.e("citiesList1", sortedCities.toString())

            }
        }


    }

    private fun handleLoadingContent() {
        viewModel.isLoading.observe(this@MainActivity) {
            if (it == true) {
                viewBinding.progressBar.isVisible = true
                viewBinding.citiesRV.isVisible = false
            } else {
                viewBinding.progressBar.isVisible = false
                viewBinding.citiesRV.isVisible = true
            }
        }
    }

    private fun settingRvAdapter() {
        viewBinding.citiesRV.adapter = citiesAdapter
    }
}