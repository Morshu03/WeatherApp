package com.example.weatherapp.util

import com.example.weatherapp.data.entity.GeocodingResponse
import com.example.weatherapp.presentation.screen.manageLocation.model.CityName
import com.example.weatherapp.presentation.screen.manageLocation.model.CityNameView

object CityNameViewMapper {

    fun List<GeocodingResponse.NameResponse>?.toView() = CityNameView(
        namesCitiesList = this?.map {
             CityName(
                 name = it.name.toString()
             )
        }
    )

}