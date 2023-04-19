package com.example.weatherapp.presentation.screen.weatherPage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.weatherPage.model.HourlyWeatherView

class WeatherAdapter(private var weatherList: List<HourlyWeatherView> = listOf()) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceType")
        fun bind(hourlyWeatherView: HourlyWeatherView) {
            val timeText: TextView = itemView.findViewById(R.id.time)
            val minimumOfTemperatureInCurrentHourText: TextView =
                itemView.findViewById(R.id.minimumOfTemperatureInCurrentHour)
            val maximumOfTemperatureInCurrentHourText: TextView =
                itemView.findViewById(R.id.maximumOfTemperatureInCurrentHour)
            val precipitationIPercentOnHourText: TextView =
                itemView.findViewById(R.id.precipitationIPercentOnHour)
            val weatherDependingOnTheTimeInPicture: ImageView =
                itemView.findViewById(R.id.weatherDependingOnTheTimeInPicture)

            timeText.text = hourlyWeatherView.timeText
            minimumOfTemperatureInCurrentHourText.text = hourlyWeatherView.minimumOfTemperatureInCurrentHourText
            maximumOfTemperatureInCurrentHourText.text = hourlyWeatherView.maximumOfTemperatureInCurrentHourText
            precipitationIPercentOnHourText.text = hourlyWeatherView.precipitationIPercentOnHourText

            Glide.with(itemView.context).load(hourlyWeatherView.weatherDependingOnTheTimeInPicture).centerCrop()
                .into(weatherDependingOnTheTimeInPicture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }
}