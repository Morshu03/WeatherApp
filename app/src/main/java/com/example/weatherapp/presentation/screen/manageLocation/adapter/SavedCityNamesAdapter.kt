package com.example.weatherapp.presentation.screen.manageLocation.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.manageLocation.model.CityNameItem


class SavedCityNamesAdapter: RecyclerView.Adapter<SavedCityNamesAdapter.ViewHolder>() {
    private val savedCitiesList: List<CityNameItem> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.savedCityName)
        val country : TextView = itemView.findViewById(R.id.countryOfSavedCityName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCityNamesAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SavedCityNamesAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}