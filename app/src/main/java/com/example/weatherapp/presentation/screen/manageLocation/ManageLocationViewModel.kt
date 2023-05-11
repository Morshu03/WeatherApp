package com.example.weatherapp.presentation.screen.manageLocation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.manageLocation.model.ManageLocationUiState
import com.example.weatherapp.util.CityNameViewMapper.toItems
import com.example.weatherapp.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageLocationViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    val citiesUiStateLiveData: MutableLiveData<ManageLocationUiState> = MutableLiveData()

    fun fetchCities(cityName: String) {
        viewModelScope.launch() {
            when (val response = repository.getCityName(
                cityName = cityName
            )) {
                is RequestResult.Success -> {
                    citiesUiStateLiveData.postValue(
                        ManageLocationUiState.Success(
                            searchCityList = response.data.toItems()
                        )
                    )
                }

                is RequestResult.Error -> {
                    citiesUiStateLiveData.postValue(
                        ManageLocationUiState.Error(
                            message = response.message ?: ""
                        )
                    )
                }
            }
        }
    }
}