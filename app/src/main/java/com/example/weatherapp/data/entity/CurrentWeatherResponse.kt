package com.example.weatherapp.data.entity

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val weather: List<Weather>?,
    val main: Main?,
    val wind: Wind?,
    val clouds: Clouds?,
    val message: String?
) {

    data class Weather(
        val main: String?,
        val description: String?
    )

    data class Main(
        val temp: Double?,
        val pressure: Int?,
        val humidity: Int?
    )

    data class Wind(
        val speed: Double?
    )

    data class Clouds(
        @SerializedName("all")
        val cloudiness: Int?
    )
}