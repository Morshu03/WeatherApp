package com.example.weatherapp.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.data.entity.Location
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocationRepository @Inject constructor(private val application: Application) {
    val currentLocation = MutableLiveData<Location?>(null)

    private val mFusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    fun updateLocation() {
        if (checkPermission()) {
            mFusedLocationClient.lastLocation.addOnCompleteListener  { task ->
                val location: android.location.Location? = task.result
                if (location != null) {
                    currentLocation.postValue(Location(location.latitude, location.longitude))
                }
            }
        } else {
            checkPermission()
        }
    }

    private fun checkPermission(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                application,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                application,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }
}