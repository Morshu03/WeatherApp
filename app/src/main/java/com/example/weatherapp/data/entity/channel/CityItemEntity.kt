package com.example.weatherapp.data.entity.channel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem

@Entity
data class CityItemEntity(
    @PrimaryKey(autoGenerate = true)
    val cityItem: CityItem
)
