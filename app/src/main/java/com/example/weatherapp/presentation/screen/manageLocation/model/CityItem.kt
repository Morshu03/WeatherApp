package com.example.weatherapp.presentation.screen.manageLocation.model

import com.example.weatherapp.data.db.entity.CityItemEntity

data class CityItem(
    var name: String,
    var country: String,
    var lat: Double,
    var lon: Double
)
    fun CityItem.toDbEntity() = CityItemEntity(
        name = this.name,
        country = this.country,
        lat = this.lat,
        lon = this.lon
    )

    fun CityItemEntity.toView() = CityItem(
        name = this.name,
        country = this.country,
        lat = this.lat,
        lon = this.lon
    )
