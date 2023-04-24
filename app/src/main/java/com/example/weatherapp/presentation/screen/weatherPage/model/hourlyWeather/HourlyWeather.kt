package com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather

import android.widget.ImageView
import androidx.annotation.DrawableRes

data class HourlyWeather(
    val timeText: String,
    val minTemp: Double,
    val maxTemp: Double,
    val precipitation: Double,
    @DrawableRes val weatherIconId: Int
)
