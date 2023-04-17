package com.example.weatherapp.presentation.screen.weatherPage.model


sealed class WeatherPageUiState {
    data class Success(
       val weatherView: WeatherPageView
    ) : WeatherPageUiState()

    data class Error(
        val message: String
    ) : WeatherPageUiState()
}
