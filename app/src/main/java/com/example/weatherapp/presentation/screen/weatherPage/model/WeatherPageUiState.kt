package com.example.weatherapp.presentation.screen.weatherPage.model


sealed class WeatherPageUiState {
    data class Success(
        val main: String,
        val description: String,
        val icon: Int,

        val temp: Double,
        val pressure: Int,
        val humidity: Int,
        val speed: Double,
        val durationOfRain: Double,
        val cloudiness: Int
    ) : WeatherPageUiState()

    data class Error(
        val message: String
    ) : WeatherPageUiState()
}
