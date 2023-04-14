package com.example.weatherapp.presentation.screen.weatherPage.model


sealed class WeatherPageUiState {
    data class Success(
        val description: String,
        val icon: Int,
        val weatherType: String,
        val temp: Int,
        val pressure: Int,
        val humidity: Int,
        val speed: Double,
        val dayOfWeek: String? = "Суббота",
        val date: String? = "Апр 15",
        val durationOfRain: Double,
        val cloudiness: Int
    ) : WeatherPageUiState()

    data class Error(
        val message: String
    ) : WeatherPageUiState()
}
