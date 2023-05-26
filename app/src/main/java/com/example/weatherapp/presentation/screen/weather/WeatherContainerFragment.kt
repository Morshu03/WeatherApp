package com.example.weatherapp.presentation.screen.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherContainerBinding
import com.example.weatherapp.presentation.screen.weatherPage.WeatherPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherContainerFragment : Fragment() {
    private var _binding: FragmentWeatherContainerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewCityButton.setOnClickListener {
            findNavController().navigate(R.id.action_weatherContainerFragment_to_manageLocationFragment)
        }

        val currentCity = requireArguments().getString("cityArgument")
        val latCity = requireArguments().getFloat("latArgument")
        val lonCity = requireArguments().getFloat("lonArgument")

        val cityBundle = Bundle()
        cityBundle.apply {
            putFloat("latitude", latCity)
            putFloat("longitude", lonCity)
        }

        val weatherPageFragment = WeatherPageFragment()
        weatherPageFragment.arguments = cityBundle

        if (currentCity.isNullOrEmpty()) {
            binding.currentCityName.text = ""
        } else {
            binding.currentCityName.text = currentCity
        }

        childFragmentManager.beginTransaction().replace(R.id.container, weatherPageFragment).commit()
    }
}