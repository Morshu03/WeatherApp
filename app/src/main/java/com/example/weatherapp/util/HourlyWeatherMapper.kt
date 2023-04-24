package com.example.weatherapp.util

import com.example.weatherapp.R
import com.example.weatherapp.data.entity.HourlyWeatherResponse
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyPageView
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyWeather
import java.text.SimpleDateFormat
import java.util.Date

object HourlyWeatherMapper {

    fun HourlyWeatherResponse?.toView() = HourlyPageView(
        hourlyList = this?.list?.map {
            val formatter = SimpleDateFormat("HH:mm")
            val dateString = formatter.format(Date(it.dt?.times(1000) ?: 0 ))
            HourlyWeather(
                timeText = dateString,
                maxTemp = it.main?.maximumTemperature ?: 0.0,
                minTemp = it.main?.minimumTemperature ?: 0.0,
                precipitation = it.rain?.durationOfRain ?: 0.0,
                weatherIconId = it.weather?.firstOrNull()?.icon.getIconId()
            )
        }
    )

    private fun String?.getIconId() : Int {
        return when (this) {
            "01d" -> R.drawable.weather_sunny
            "01n" -> R.drawable.night_demilune_icon
            "02d" -> R.drawable.weather_partly_cloudy_day
            "02n" -> R.drawable.weather_partly_cloudy_night
            "03d" -> R.drawable.weather_partly_cloudy_day
            "03n" -> R.drawable.weather_partly_cloudy_night
            "04d" -> R.drawable.weather_partly_cloudy_day
            "04n" -> R.drawable.weather_partly_cloudy_night
            "09d" -> R.drawable.weather_rain_showers_day
            "09n" -> R.drawable.weather_rain_showers_night
            "10d" -> R.drawable.weather_rain_showers_day
            "10n" -> R.drawable.weather_rain_showers_night
            "11d" -> R.drawable.weather_rain_showers_day
            "11n" -> R.drawable.weather_rain_showers_night
            "13d" -> R.drawable.weather_snow_shower_day
            "13n" -> R.drawable.weather_snow_shower_night
            "50d" -> R.drawable.cloud_fog
            "50n" -> R.drawable.cloud_fog
            else -> R.drawable.weather_rain_showers_day
        }
    }
}