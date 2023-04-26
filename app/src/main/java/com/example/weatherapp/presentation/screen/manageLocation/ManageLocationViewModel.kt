package com.example.weatherapp.presentation.screen.manageLocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.LocationRepository
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.manageLocation.model.CitiesNames
import com.example.weatherapp.presentation.screen.manageLocation.model.CitiesNamesUiState
import com.example.weatherapp.presentation.screen.manageLocation.model.CitiesNamesView
import com.example.weatherapp.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageLocationViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {
    private  val citiesUiStateLiveData: MutableLiveData<CitiesNamesUiState> = MutableLiveData()

    fun fetchCities(cityName: CitiesNames) {
        viewModelScope.launch {
            when (val response = repository.getCityName(
                cityName = cityName.name)) {
                is RequestResult.Success -> {
                    response.data?.let {
                        citiesUiStateLiveData.postValue(
                            CitiesNamesUiState.Success()
                        )
                    }
                }
                is RequestResult.Error -> {
                    citiesUiStateLiveData.postValue(
                        CitiesNamesUiState.Error(
                            message = response.message ?: ""
                        )
                    )
                }
            }
        }
    }
}