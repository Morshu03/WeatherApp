package com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather

data class HourlyWeather(
    val timeText: String,
    val minimumOfTemperatureInCurrentHourText: String,
    val maximumOfTemperatureInCurrentHourText: String,
    val precipitationIPercentOnHourText: String,
    val weatherDependingOnTheTimeInPicture: Int
)
