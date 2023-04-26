package com.example.weatherapp.presentation.screen.manageLocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.manageLocation.model.CitiesNames

class ManageLocationAdapter(private var citiesList: List<CitiesNames> = listOf()): RecyclerView.Adapter<ManageLocationAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(citiesNames: CitiesNames){
            val names: TextView = itemView.findViewById(R.id.cityName)

            names.text = citiesNames.name
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

    fun setList(newCitiesList: MutableList<CitiesNames>) {
        citiesList = newCitiesList
        notifyDataSetChanged()
    }
}