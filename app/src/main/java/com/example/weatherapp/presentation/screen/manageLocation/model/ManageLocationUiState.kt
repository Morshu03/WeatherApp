package com.example.weatherapp.presentation.screen.manageLocation.model

sealed class ManageLocationUiState {

    data class Success(
        val searchCityList: List<CityItem>? = listOf()
    ) : ManageLocationUiState()

    data class Error(
        val message: String
    ) : ManageLocationUiState()

}


