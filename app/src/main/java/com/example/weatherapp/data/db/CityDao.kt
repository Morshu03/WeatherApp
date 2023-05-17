package com.example.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.data.entity.channel.CityItemEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cityitementity")
    fun getCityItem(): List<CityItemEntity>

    @Insert
    fun insertCityItem(cityitementity: List<CityItemEntity>)
}