package com.example.weatherapp.presentation.screen.weatherPage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyWeather

class WeatherAdapter(private var weatherList: List<HourlyWeather> = listOf()) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val res = itemView.resources
        fun bind(hourlyWeather: HourlyWeather) {
            val timeText: TextView = itemView.findViewById(R.id.time)
            val minimumOfTemperatureInCurrentHourText: TextView =
                itemView.findViewById(R.id.minimumOfTemperatureInCurrentHour)
            val maximumOfTemperatureInCurrentHourText: TextView =
                itemView.findViewById(R.id.maximumOfTemperatureInCurrentHour)
            val precipitationIPercentOnHourText: TextView =
                itemView.findViewById(R.id.precipitationIPercentOnHour)
            val weatherDependingOnTheTimeInPicture: ImageView =
                itemView.findViewById(R.id.weatherDependingOnTheTimeInPicture)

            timeText.text = hourlyWeather.timeText
            minimumOfTemperatureInCurrentHourText.text = hourlyWeather.minTemp.toInt().toString()
            maximumOfTemperatureInCurrentHourText.text = hourlyWeather.maxTemp.toInt().toString()
            precipitationIPercentOnHourText.text =
                String.format(res.getString(R.string.duration_of_rain), hourlyWeather.precipitation.toInt().toString())
            weatherDependingOnTheTimeInPicture.setImageResource(hourlyWeather.weatherIconId)
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

    fun setList(newWeatherList: MutableList<HourlyWeather>) {
        weatherList = newWeatherList
        notifyDataSetChanged()
    }
}
