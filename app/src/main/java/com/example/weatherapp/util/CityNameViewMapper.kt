package com.example.weatherapp.util

import com.example.weatherapp.data.entity.GeocodingResponse
import com.example.weatherapp.presentation.screen.manageLocation.model.CityNameItem
import com.example.weatherapp.presentation.screen.manageLocation.model.CityNameView

object CityNameViewMapper {

    fun List<GeocodingResponse.NameResponse>?.toView() = CityNameView(
        namesCitiesList = this?.map {
             CityNameItem(
                 name = it.name.toString(),
                 country = it.country.toString()
             )
        }
    )

}