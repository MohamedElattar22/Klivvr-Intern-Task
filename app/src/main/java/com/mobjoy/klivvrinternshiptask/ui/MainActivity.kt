package com.mobjoy.klivvrinternshiptask.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mobjoy.klivvrinternshiptask.adapters.CitiesAdapter
import com.mobjoy.klivvrinternshiptask.databinding.ActivityMainBinding
import com.mobjoy.klivvrinternshiptask.viewmodels.CitiesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: CitiesViewModel
    private lateinit var sortedCities: List<com.mobjoy.domain.model.CitiesResponseItem>
    private var citiesAdapter = CitiesAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        settingUpViewBinding()
        settingUpViewModel()
        utilizingViewModel()
        settingRvAdapter()
        settingSearch()
    }

    private fun utilizingViewModel() {
        viewModel.getAllCities()
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.citiesList.collect { data ->
                if (data[0].name != null) {
                    handleLoadingContent()
                    citiesAdapter.bindCitiesList(data)
                    sortedCities = data
                }
            }
        }
    }

    private fun handleLoadingContent() {
        viewModel.isLoading.observe(this@MainActivity) {
            if (it == true) {
                viewBinding.progressBar.isVisible = true
                viewBinding.citiesRV.isVisible = false
                viewBinding.search.visibility = View.INVISIBLE

            } else {
                viewBinding.progressBar.isVisible = false
                viewBinding.citiesRV.isVisible = true
                viewBinding.search.visibility = View.VISIBLE
            }
        }
    }

    private fun settingRvAdapter() {
        viewBinding.citiesRV.adapter = citiesAdapter
        citiesAdapter.onCityClickListener =
            CitiesAdapter.OnCityClickListener { cityItem, position ->
                val uri =
                    "geo:${cityItem.coord?.lat}," +
                            "${cityItem.coord?.lon}?q=${cityItem.coord?.lat}" +
                            ",${cityItem.coord?.lon}(${cityItem.name}, ${cityItem.country})"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                startActivity(intent)

            }
    }

    private fun settingUpViewBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    private fun settingUpViewModel() {
        viewModel = ViewModelProvider(this)[CitiesViewModel::class.java]
    }

    private fun settingSearch() {

        viewBinding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val list = mutableListOf<com.mobjoy.domain.model.CitiesResponseItem>()
                if (!newText.isNullOrBlank()) {
                    val result = viewModel.getDataFromTrie(query = newText)
                    val cityMap = viewModel.getCityMap()
                    result.forEach { city ->
                        cityMap[city]?.let {
                            list.add(it)
                        }
                    }
                    citiesAdapter.bindCitiesList(list)
                } else {
                    list.clear()
                    citiesAdapter.bindCitiesList(sortedCities)
                }
                return true

            }
        }
        )


    }
}



