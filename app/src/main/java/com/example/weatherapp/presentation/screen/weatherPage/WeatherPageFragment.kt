package com.example.weatherapp.presentation.screen.weatherPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherPageBinding
import com.example.weatherapp.presentation.screen.weatherPage.adapter.WeatherAdapter
import com.example.weatherapp.presentation.screen.weatherPage.model.currentWeather.WeatherPageUiState
import com.example.weatherapp.presentation.screen.weatherPage.model.currentWeather.WeatherPageView
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyPageUiState
import com.example.weatherapp.presentation.screen.weatherPage.model.hourlyWeather.HourlyWeather
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherPageFragment : Fragment() {

    private val viewModel: WeatherPageViewModel by viewModels()
    private var _binding: FragmentWeatherPageBinding? = null
    private val binding get() = _binding!!
    private val adapter = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        viewModel.hourlyUiStateLiveData.observe(viewLifecycleOwner){
            when(it){
                is HourlyPageUiState.Error -> {
                    showToast(it.message)
                }
                is HourlyPageUiState.Success -> {
                    adapter.setList(newWeatherList = it.hourlyView.hourlyList!!.toMutableList())
                }
            }
        }

        viewModel.weatherUiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is WeatherPageUiState.Error -> {
                    showToast(it.message)
                }

                is WeatherPageUiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE
                    updateCurrentWeatherUiState(it.weatherView)
                }
            }
        }
    }

    private fun updateCurrentWeatherUiState(weatherView: WeatherPageView) {
        binding.currentTemperature.text = weatherView.temp.toString()
        binding.currentWeather.text = weatherView.description
        binding.windSpeedTextView.text =
            getString(R.string.wind_speed_format, weatherView.speed.toString())
        binding.mercuryPressureTextView.text =
            getString(R.string.mercury_pressure_format, weatherView.pressure.toString())
        binding.chanceOfRainInPercentTextView.text =
            getString(R.string.cloudiness_in_percent_format, weatherView.cloudiness.toString())
        binding.humidityInPercentTextView.text =
            getString(R.string.humidity_in_percent_format, weatherView.humidity.toString())
        binding.currentWeekDay.text = weatherView.dayOfWeek
        binding.currentMonthAndNumberOfMonth.text = weatherView.date
        binding.currentWeekDayOverRecyclerView.text = weatherView.dayOfWeek
        binding.currentMonthAndNumberOfMonthOverRecyclerView.text = weatherView.date
    }


    override fun onResume() {
        super.onResume()
        viewModel.updateLocation()
    }

    private fun showToast(text: String) {
        Toast.makeText(
            activity, text, Toast.LENGTH_SHORT
        ).show()
    }
}