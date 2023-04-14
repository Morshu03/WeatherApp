package com.example.weatherapp.presentation.screen.weatherPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.weatherPage.model.WeatherPageUiState
import com.example.weatherapp.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class WeatherPageViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    val weatherUiStateLiveData: MutableLiveData<WeatherPageUiState> = MutableLiveData()

    init {
        fetchWeather()
    }

    fun fetchWeather(){
        viewModelScope.launch() {
            // получить геолокацию (прогуглить как получить application Context в viewmodel)
            when (val response = repository.getCurrentWeatherConditions(
                lat = 44.34,
                lon = 10.99
            )){
                is RequestResult.Success -> {
                    // получить и отформатировать даты
                    weatherUiStateLiveData.postValue(
                        WeatherPageUiState.Success(
                            temp = response.data?.main?.temp?.toInt() ?: 0,
                            pressure = response.data?.main?.pressure ?: 0,
                            humidity = response.data?.main?.humidity ?: 0,
                            weatherType = response.data?.weather?.firstOrNull()?.main ?: "",
                            speed = response.data?.wind?.speed ?: 0.0,
                            durationOfRain = response.data?.rain?.durationOfRain ?:0.0,
                            cloudiness = response.data?.clouds?.cloudiness ?: 0,
                            description = response.data?.weather?.firstOrNull()?.description ?:"",
                            icon = response.data?.weather?.firstOrNull()?.icon ?: 0
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
}