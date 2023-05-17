package com.example.weatherapp.presentation.screen.manageLocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem
import com.example.weatherapp.presentation.screen.manageLocation.model.SavedItemClickListener


class SavedCitiesAdapter(private val recyclerViewSavedItemInterface: SavedItemClickListener) :
    RecyclerView.Adapter<SavedCitiesAdapter.ViewHolder>() {

    private var savedCitiesList: List<CityItem> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val res = itemView.resources
        fun bind(citiesNames: CityItem) {
            val name: TextView = itemView.findViewById(R.id.savedCityName)
            val country: TextView = itemView.findViewById(R.id.countryOfSavedCityName)

            country.text = citiesNames.country
            name.text = citiesNames.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_saved_city_names, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(savedCitiesList[position])
        holder.itemView.setOnClickListener{
            recyclerViewSavedItemInterface.onSavedItemClick(savedCitiesList[position])
        }
    }

    override fun getItemCount(): Int {
        return savedCitiesList.size
    }

    fun setList(newSavedCitiesList: MutableList<CityItem>) {
        savedCitiesList = newSavedCitiesList
        notifyDataSetChanged()
    }
}