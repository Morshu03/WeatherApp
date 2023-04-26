package com.example.weatherapp.presentation.screen.weatherPage.model.currentWeather

import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyPageView


sealed class WeatherPageUiState {
    data class Success(
       val weatherView: WeatherPageView,
       val hourlyPageView: HourlyPageView = HourlyPageView(listOf())
    ) : WeatherPageUiState()

    data class Error(
        val message: String
    ) : WeatherPageUiState()
}
