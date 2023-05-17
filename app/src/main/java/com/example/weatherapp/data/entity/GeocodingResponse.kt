package com.example.weatherapp.data.entity

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    val list: List<NameResponse>?,
    val message: String?
) {
    data class NameResponse(
        val name: String?,
        @SerializedName("local_name")
        val localNames: LocalNames?,
        val country: String?,
        val lat: Double?,
        val lon: Double?
    )

    data class LocalNames(
        @SerializedName("ru")
        val russian: String?
    )
}
