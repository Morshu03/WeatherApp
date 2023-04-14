package com.example.weatherapp.presentation.screen.weatherPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherPageBinding
import com.example.weatherapp.presentation.screen.weatherPage.model.WeatherPageUiState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherPageFragment : Fragment() {

    private val viewModel: WeatherPageViewModel by viewModels()
    private var _binding: FragmentWeatherPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.weatherUiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is WeatherPageUiState.Error -> {
                    showToast(it.message)
                }
                is WeatherPageUiState.Success -> {
                    binding.currentTemperature.text = it.temp.toString()
                    binding.currentWeather.text = it.description
                    binding.windSpeedTextView.text = it.speed.toString()
                    binding.mercuryPressureTextView.text = it.pressure.toString()
                    binding.chanceOfRainInPercentTextView.text = it.durationOfRain.toString()
                    binding.humidityInPercentTextView.text = it.humidity.toString()
                    binding.currentWeekDay.text = it.dayOfWeek
                    binding.currentMonthAndNumberOfMonth.text = it.date
                }
            }
        }
        viewModel.fetchWeather()
    }

    private fun showToast(text: String) {
        Toast.makeText(
            activity, text, Toast.LENGTH_SHORT
        ).show()
    }
}