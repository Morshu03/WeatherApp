package com.example.weatherapp.data.api

import com.example.weatherapp.data.entity.CurrentWeatherResponse
import com.example.weatherapp.data.entity.HourlyWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherService {
    @GET("https://api.openweathermap.org/data/2.5/weather")
    suspend fun getCurrentWeatherConditions(
        @Query("appid") apiKey: String = "e5562ca7f88c0374eae70dd64cdf15a2",
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric"
    ): Response<CurrentWeatherResponse>

    @GET("https://pro.openweathermap.org/data/2.5/forecast/hourly")
    suspend fun getHourlyWeatherConditions(
        @Query("appid") apiKey: String = "e5562ca7f88c0374eae70dd64cdf15a2",
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String = "ru",
        @Query("units") units: String = "metric"
    ): Response<HourlyWeatherResponse>
}