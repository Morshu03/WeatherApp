package com.example.weatherapp.util

import com.example.weatherapp.data.entity.GeocodingResponse
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem

object CityNameViewMapper {

    fun List<GeocodingResponse.NameResponse>?.toItems() =
        this?.map {
             CityItem(
                 name = it.name.toString(),
                 country = it.country.toString(),
                 lat = it.lat ?: 0.0,
                 lon = it.lon ?: 0.0
             )
        }
}