package com.example.weatherapp.data.repository

import com.example.weatherapp.data.entity.CurrentWeatherResponse
import com.example.weatherapp.data.api.WeatherService
import com.example.weatherapp.util.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getCurrentWeatherConditions(lat: Double, lon: Double): RequestResult<CurrentWeatherResponse?> {
        return withContext(Dispatchers.IO) {
            try {
                val result = weatherService.getCurrentWeatherConditions(
                    lat = lat,
                    lon = lon
                )
                if (result.isSuccessful) {
                    result.body()?.let {
                        return@withContext RequestResult.Success(it)
                    }
                }
                    return@withContext RequestResult.Error(result.message())
            } catch (e: java.lang.Exception) {
                return@withContext RequestResult.Error(e.message)
            }
        }
    }

    companion object {
        private const val API_KEY = "e5562ca7f88c0374eae70dd64cdf15a2"
    }
}