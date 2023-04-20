package com.example.weatherapp.util

import com.example.weatherapp.R
import com.example.weatherapp.data.entity.HourlyWeatherResponse
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyPageView
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyWeather

object HourlyWeatherMapper {

    fun HourlyWeatherResponse?.toView() = HourlyPageView(
        hourlyList = this?.list?.map {
            HourlyWeather(
                timeText = it.dt.toString(),
                maximumOfTemperatureInCurrentHourText = it.main.maximumTemperature.toString(),
                minimumOfTemperatureInCurrentHourText = it.main.minimumTemperature.toString(),
                precipitationIPercentOnHourText = it.rain.durationOfRain.toString(),
                weatherDependingOnTheTimeInPicture = R.drawable.weather_rain_showers_day
            )
        }
    )
}