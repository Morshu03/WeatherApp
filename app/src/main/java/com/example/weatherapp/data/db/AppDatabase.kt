package com.example.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.entity.channel.CityItemEntity

@Database(entities = [CityItemEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun cityDao(): CityDao

}