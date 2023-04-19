package com.example.weatherapp.data.entity

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HourlyWeatherResponse(
    val weather: List<Weather>,
    val main: Main,
    val rain: Rain,
    val date: Date
) {

    data class Weather(
        val main: String,
        val description: String,
        val icon: Int
    )

    data class Main(
        @SerializedName("temp_min")
        val minimumTemperature: Double,
        @SerializedName("temp_max")
        val maximumTemperature: Double,
    )

    data class Rain(
        @SerializedName("1h")
        val durationOfRain: Double
    )

    data class Date(
        @SerializedName("dt_txt")
        val date: java.util.Date
    )
}
