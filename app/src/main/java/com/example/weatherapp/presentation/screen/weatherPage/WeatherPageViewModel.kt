package com.example.weatherapp.presentation.screen.weatherPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.entity.Location
import com.example.weatherapp.data.repository.LocationRepository
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.weatherPage.model.WeatherPageUiState
import com.example.weatherapp.util.CurrentWeatherMapper.toView
import com.example.weatherapp.util.DateUtil.formatCurrentDay
import com.example.weatherapp.util.DateUtil.formatDayOfWeek
import com.example.weatherapp.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


@HiltViewModel
class WeatherPageViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {
    val weatherUiStateLiveData: MutableLiveData<WeatherPageUiState> = MutableLiveData()

    private val locationObserver = Observer<Location?> { location ->
        location?.let {
            fetchWeather(it)
        }
    }

    init {
        locationRepository.currentLocation.observeForever(locationObserver)
    }

    fun updateLocation() {
        viewModelScope.launch {
            locationRepository.updateLocation()
        }
    }

    fun fetchWeather(location: Location) {
        viewModelScope.launch() {
            when (val response = repository.getCurrentWeatherConditions(
                lat = location.lat,
                lon = location.lon
            )) {
                is RequestResult.Success -> {
                    val date = LocalDate.now()
                    val currentDate = date.formatCurrentDay()
                    val dayOfWeek = date.formatDayOfWeek()
                    weatherUiStateLiveData.postValue(
                        WeatherPageUiState.Success(
                            weatherView = response.data.toView(currentDate, dayOfWeek)
                        )
                    )
                }

                is RequestResult.Error -> {
                    weatherUiStateLiveData.postValue(
                        WeatherPageUiState.Error(
                            message = response.message ?: ""
                        )
                    )
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        locationRepository.currentLocation.removeObserver(locationObserver)
    }
}