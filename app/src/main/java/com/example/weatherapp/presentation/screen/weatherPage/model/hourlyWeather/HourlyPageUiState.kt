package com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather

sealed class HourlyPageUiState{
    data class Success(
        val hourlyView: HourlyPageView
    ) : HourlyPageUiState()

    data class Error(
        val message: String
    ) : HourlyPageUiState()
}
