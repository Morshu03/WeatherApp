package com.example.weatherapp.presentation.screen.weatherPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.presentation.screen.weatherPage.model.WeatherPageUiState
import com.example.weatherapp.util.RequestResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
            when (val response = repository.getCurrentWeatherConditions()){
                is RequestResult.Success -> {
                    weatherUiStateLiveData.postValue(
                        WeatherPageUiState.Success(
                            temp = (response.data?.main?.temp ?: "") as Double,
                            pressure = (response.data?.main?.pressure ?: "") as Int,
                            humidity = (response.data?.main?.humidity ?: "") as Int,
                            speed = (response.data?.wind?.speed ?: "") as Double,
                            durationOfRain = (response.data?.rain?.durationOfRain ?:"") as Double,
                            cloudiness = (response.data?.clouds?.cloudiness ?:"") as Int,
                            main = (response.data?.weather?.get(0) ?:"") as String,
                            description = (response.data?.weather?.get(1) ?:"") as String,
                            icon = (response.data?.weather?.get(2) ?:"") as Int
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