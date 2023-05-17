package com.example.weatherapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherapp.data.db.entity.CityItemEntity

@Dao
interface CityDao {
    @Query("SELECT * FROM cityitementity")
    fun getCityItems(): List<CityItemEntity>

    @Insert
    fun insertCityItem(cityitementity: List<CityItemEntity>)
}