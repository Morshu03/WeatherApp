package com.example.weatherapp.presentation.screen.weatherPage.model.currentWeather

data class WeatherPageView(
    val description: String,
    val icon: Int,
    val weatherType: String,
    val temp: Int,
    val pressure: Int,
    val humidity: Int,
    val speed: Double,
    val dayOfWeek: String? = "суббота",
    val date: String? = "Апр 15",
    val durationOfRain: Double,
    val cloudiness: Int
)
