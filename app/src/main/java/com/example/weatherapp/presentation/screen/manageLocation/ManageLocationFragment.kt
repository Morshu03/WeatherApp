package com.example.weatherapp.presentation.screen.manageLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentManageLocationBinding
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.screen.manageLocation.adapter.ManageLocationAdapter

class ManageLocationFragment : Fragment(), OnQueryTextListener {
    private var _binding: FragmentManageLocationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ManageLocationViewModel by viewModels()
    private val adapter = ManageLocationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_manageLocationFragment_to_weatherFragment)
        }
        binding.manageLocationSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // viewModel.fetchCities(newText)
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO("Not yet implemented")
    }
}