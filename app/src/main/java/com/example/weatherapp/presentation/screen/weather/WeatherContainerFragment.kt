package com.example.weatherapp.presentation.screen.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNewCityButton.setOnClickListener {
            findNavController().navigate(R.id.action_weatherFragment_to_manageLocationFragment)
        }
        // binding.currentCityName.text =
        childFragmentManager
            .beginTransaction()
            .replace(R.id.container, WeatherPageFragment())
            .commit()
    }

}