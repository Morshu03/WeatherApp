package com.example.weatherapp.presentation.screen.manageLocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem
import com.example.weatherapp.presentation.screen.manageLocation.model.SearchClickListener

class SearchViewAdapter(val recyclerViewInterface: SearchClickListener) :
    RecyclerView.Adapter<SearchViewAdapter.MyViewHolder>() {
    private var citiesList: List<CityItem> = listOf()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val res = itemView.resources
        fun bind(citiesNames: CityItem) {
            val names: TextView = itemView.findViewById(R.id.cityName)
            val country: TextView = itemView.findViewById(R.id.country)

            country.text = String.format(res.getString(R.string.country), citiesNames.country)
            names.text = String.format(res.getString(R.string.city_name), citiesNames.name)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_city_name_search_view, parent, false)
        return MyViewHolder(view)
    }
    override fun getItemCount(): Int {
        return citiesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(citiesList[position])
        holder.itemView.setOnClickListener{
            recyclerViewInterface.onSearchItemClick(citiesList[position])
        }
    }

    fun setList(newCitiesList: MutableList<CityItem>) {
        citiesList = newCitiesList
        notifyDataSetChanged()
    }
}