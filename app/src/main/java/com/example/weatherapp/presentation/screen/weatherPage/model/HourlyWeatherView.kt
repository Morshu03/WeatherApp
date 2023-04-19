package com.example.weatherapp.presentation.screen.weatherPage.model

import android.widget.ImageView

data class HourlyWeatherView(
    val timeText: String,
    val minimumOfTemperatureInCurrentHourText: String,
    val maximumOfTemperatureInCurrentHourText: String,
    val precipitationIPercentOnHourText: String,
    val weatherDependingOnTheTimeInPicture: Int
)
