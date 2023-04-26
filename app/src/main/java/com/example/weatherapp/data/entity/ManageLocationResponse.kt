package com.example.weatherapp.data.entity

import com.google.gson.annotations.SerializedName

data class ManageLocationResponse(
    val list: List<NameResponse>?,
    val message: String?
) {
    data class NameResponse(
        val name: Name?,
        @SerializedName("local_name")
        val localNames: LocalNames?
    )

    data class Name(
        val name: String?
    )

    data class LocalNames(
        @SerializedName("ru")
        val russian: String?
    )
}
