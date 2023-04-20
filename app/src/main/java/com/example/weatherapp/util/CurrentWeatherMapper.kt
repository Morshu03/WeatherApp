package com.example.weatherapp.util

import com.example.weatherapp.data.entity.CurrentWeatherResponse
import com.example.weatherapp.presentation.screen.weatherPage.model.currentWeather.WeatherPageView
import javax.inject.Singleton

@Singleton
object CurrentWeatherMapper {

    fun CurrentWeatherResponse?.toView(
        currentDate: String,
        dayOfWeek: String
    ) = WeatherPageView(
        temp = this?.main?.temp?.toInt() ?: 0,
        pressure = this?.main?.pressure ?: 0,
        humidity = this?.main?.humidity ?: 0,
        weatherType = this?.weather?.firstOrNull()?.main ?: "",
        speed = this?.wind?.speed ?: 0.0,
        durationOfRain = this?.rain?.durationOfRain ?: 0.0,
        cloudiness = this?.clouds?.cloudiness ?: 0,
        date = currentDate,
        dayOfWeek = dayOfWeek,
        description = this?.weather?.firstOrNull()?.description ?: "",
        icon = this?.weather?.firstOrNull()?.icon ?: 0
    )

}