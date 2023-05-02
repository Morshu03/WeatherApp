package com.example.weatherapp.presentation.screen.manageLocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.manageLocation.model.CityName
import okhttp3.internal.format

class SearchViewAdapter(private var citiesList: List<CityName> = listOf()) :
    RecyclerView.Adapter<SearchViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val res = itemView.resources
        fun bind(citiesNames: CityName) {
            val names: TextView = itemView.findViewById(R.id.cityName)

            names.text = String.format(res.getString(R.string.city_name), citiesNames.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(citiesList[position])
    }

    fun setList(newCitiesList: MutableList<CityName>) {
        citiesList = newCitiesList
        notifyDataSetChanged()
    }
}