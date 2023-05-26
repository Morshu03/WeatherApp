package com.example.weatherapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem

@Entity
data class CityItemEntity(
    @PrimaryKey
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double
)
