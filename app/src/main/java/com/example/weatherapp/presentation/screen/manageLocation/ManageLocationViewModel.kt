package com.example.weatherapp.presentation.screen.manageLocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.manageLocation.model.CityNameItem
import com.example.weatherapp.presentation.screen.manageLocation.model.CityNameUiState
import com.example.weatherapp.util.CityNameViewMapper.toView
import com.example.weatherapp.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageLocationViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val citiesUiStateLiveData: MutableLiveData<CityNameUiState> = MutableLiveData()

    fun fetchCities(cityName: String) {
        viewModelScope.launch() {
            when (val response = repository.getCityName(
                cityName = cityName
            )) {
                is RequestResult.Success -> {
                    citiesUiStateLiveData.postValue(
                        CityNameUiState.Success(
                            citiesNamesView = response.data.toView()
                        )
                    )
                }

                is RequestResult.Error -> {
                    citiesUiStateLiveData.postValue(
                        CityNameUiState.Error(
                            message = response.message ?: ""
                        )
                    )
                }
            }
        }
    }
}