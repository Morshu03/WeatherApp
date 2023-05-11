package com.example.weatherapp.presentation.screen.manageLocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentManageLocationBinding
import com.example.weatherapp.presentation.screen.manageLocation.adapter.SearchViewAdapter
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem
import com.example.weatherapp.presentation.screen.manageLocation.model.ManageLocationUiState
import com.example.weatherapp.presentation.screen.manageLocation.model.SearchClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageLocationFragment : Fragment(), SearchClickListener {
    private var _binding: FragmentManageLocationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ManageLocationViewModel by viewModels()
    private val searchViewAdapter = SearchViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.searchViewRecycler.layoutManager = layoutManager
        binding.searchViewRecycler.adapter = searchViewAdapter

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_manageLocationFragment_to_weatherFragment)
        }

        binding.manageLocationSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.fetchCities(newText ?: "")
                return true
            }
        })
        viewModel.citiesUiStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ManageLocationUiState.Error -> {
                    showToast(it.message)
                }

                is ManageLocationUiState.Success -> {
                    searchViewAdapter.setList(it.searchCityList?.toMutableList() ?: mutableListOf())
                }
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            activity, text, Toast.LENGTH_SHORT
        ).show()
    }

    override fun onSearchItemClick(cityNameItem: CityItem) {

    }
}