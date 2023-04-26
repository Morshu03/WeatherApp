package com.example.weatherapp.presentation.screen.manageLocation.model

sealed class CitiesNamesUiState{

    data class Success(
        val citiesNamesView: CitiesNamesView = CitiesNamesView(listOf())
    ): CitiesNamesUiState()

    data class Error(
        val message: String
    ): CitiesNamesUiState()

}


