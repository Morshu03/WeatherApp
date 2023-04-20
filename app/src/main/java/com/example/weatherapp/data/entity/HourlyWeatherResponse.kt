package com.example.weatherapp.data.entity

import com.google.gson.annotations.SerializedName

data class HourlyWeatherResponse(
    val list: List<HourResponse>
) {
    data class HourResponse(
        val weather: List<Weather>,
        val main: Main,
        val rain: Rain,
        val dt: Long
    )

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
}
