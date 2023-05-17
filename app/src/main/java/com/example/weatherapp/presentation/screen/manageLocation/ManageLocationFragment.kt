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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentManageLocationBinding
import com.example.weatherapp.presentation.screen.manageLocation.adapter.SavedCitiesAdapter
import com.example.weatherapp.presentation.screen.manageLocation.adapter.SearchViewAdapter
import com.example.weatherapp.presentation.screen.manageLocation.model.CityItem
import com.example.weatherapp.presentation.screen.manageLocation.model.ManageLocationUiState
import com.example.weatherapp.presentation.screen.manageLocation.model.SavedItemClickListener
import com.example.weatherapp.presentation.screen.manageLocation.model.SearchClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageLocationFragment : Fragment(), SearchClickListener, SavedItemClickListener {
    private var _binding: FragmentManageLocationBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ManageLocationViewModel by viewModels()
    private val searchViewAdapter = SearchViewAdapter(this)
    private val savedCitiesAdapter = SavedCitiesAdapter(this)
    private val citiesList = mutableListOf<CityItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManageLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchLayoutManager = LinearLayoutManager(activity)
        val savedLayoutManager = LinearLayoutManager(activity)
        binding.searchViewRecycler.layoutManager = searchLayoutManager
        binding.searchViewRecycler.adapter = searchViewAdapter
        binding.savedCitiesRecView.layoutManager = savedLayoutManager
        binding.savedCitiesRecView.adapter = savedCitiesAdapter
        binding.savedCitiesRecView.visibility = View.VISIBLE

        binding.arrowBack.setOnClickListener {
            findNavController().navigate(R.id.action_manageLocationFragment_to_weatherContainerFragment)
        }

        binding.manageLocationSearchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.searchViewRecycler.visibility = View.GONE
                } else {
                    viewModel.fetchCities(newText)
                    binding.searchViewRecycler.visibility = View.VISIBLE
                }
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

    override fun onSearchItemClick(cityItem: CityItem) {
        citiesList.add(cityItem)
        savedCitiesAdapter.setList(citiesList)
        binding.manageLocationSearchView.setQuery("", false)
    }

    override fun onSavedItemClick(cityItem: CityItem) {
        findNavController().navigate(R.id.action_manageLocationFragment_to_weatherContainerFragment,
            Bundle().apply {
                putString("cityNameArgument", cityItem.name)
                putDouble("latArgument", cityItem.lat)
                putDouble("lonArgument", cityItem.lon)
            })
    }
}
