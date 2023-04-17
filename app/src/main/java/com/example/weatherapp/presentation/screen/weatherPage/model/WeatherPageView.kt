package com.example.weatherapp.presentation.screen.weatherPage.model

data class WeatherPageView(
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
)
