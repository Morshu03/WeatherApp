package com.example.weatherapp.data.entity.api

import retrofit2.Response
import retrofit2.http.GET

interface WeatherService {
    @GET("/data/2.5/weather?lat=44.34&lon=10.99&appid=e5562ca7f88c0374eae70dd64cdf15a2")
    suspend fun getCurrentWeatherConditions(): Response<CurrentWeatherResponse>
}