package com.example.weatherapp.presentation.screen.manageLocation.model

sealed class CityNameUiState {

    data class Success(
        val citiesNamesView: CityNameView = CityNameView(listOf())
    ) : CityNameUiState()

    data class Error(
        val message: String
    ) : CityNameUiState()

}


