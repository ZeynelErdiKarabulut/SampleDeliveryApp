package com.zerdi.sampledeliveryapp.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zerdi.sampledeliveryapp.databinding.FragmentHomeBinding
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zerdi.sampledeliveryapp.R
import com.zerdi.sampledeliveryapp.model.entity.cuisine.Cuisine
import com.zerdi.sampledeliveryapp.model.entity.restaurant.Restaurant
import com.zerdi.sampledeliveryapp.utils.Resource
import com.zerdi.sampledeliveryapp.utils.adapter.CuisineItemAdapter
import com.zerdi.sampledeliveryapp.utils.adapter.RestaurantItemAdapter
import com.zerdi.sampledeliveryapp.utils.gone
import com.zerdi.sampledeliveryapp.utils.listener.ICuisineOnClick
import com.zerdi.sampledeliveryapp.utils.listener.IRestaurantOnClick
import com.zerdi.sampledeliveryapp.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private var restaurantAdapter = RestaurantItemAdapter()
    private var cuisineAdapter = CuisineItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        addObserver()
        addListener()
    }

    private fun initView() {
        _binding.restaurantRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        _binding.cuisineRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun addObserver() {
        viewModel.getUserImage().observe(viewLifecycleOwner, { response ->
            if (response.status == Resource.Status.SUCCESS) {
                val options = RequestOptions().placeholder(R.drawable.ic_profile)
                Glide.with(_binding.profileImageButton.context)
                    .applyDefaultRequestOptions(options)
                    .load(response.data?.image).into(_binding.profileImageButton)
            }
        })
        viewModel.getRestaurantList().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.restaurantProgressBar.show()
                Resource.Status.SUCCESS -> {
                    viewModel.restaurantList = response.data?.restaurantList
                    setRestaurant(response.data?.restaurantList)
                }
                Resource.Status.ERROR -> _binding.restaurantProgressBar.gone()
            }
        })
        viewModel.getCuisineList().observe(viewLifecycleOwner, { response ->
            when (response.status) {
                Resource.Status.LOADING -> _binding.cuisineProgressBar.show()
                Resource.Status.SUCCESS -> {
                    viewModel.cuisineList = response.data?.cuisineList
                    setCuisineList(response.data?.cuisineList)
                }
                Resource.Status.ERROR -> _binding.cuisineProgressBar.gone()
            }
        })
    }

    private fun addListener() {
        restaurantAdapter.addListener(object : IRestaurantOnClick {
            override fun onClick(restaurant: Restaurant) {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToRestaurantDetailFragment(restaurant.id)
                findNavController().navigate(action)
            }
        })
        cuisineAdapter.addListener(object : ICuisineOnClick {
            override fun onClick(cuisine: Cuisine) {
                val action = HomeFragmentDirections.actionHomeFragmentToRestaurantListingFragment(
                    cuisine.id,
                    cuisine.name
                )
                findNavController().navigate(action)
            }
        })
        _binding.profileImageButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        _binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val filterRestaurantList = viewModel.searchTextOnRestaurantList(query)
                setRestaurant(filterRestaurantList)
                val filterCuisineList = viewModel.searchTextOnCuisineList(query)
                setCuisineList(filterCuisineList)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filterRestaurantList = viewModel.searchTextOnRestaurantList(newText)
                setRestaurant(filterRestaurantList)
                val filterCuisineList = viewModel.searchTextOnCuisineList(newText)
                setCuisineList(filterCuisineList)
                return true
            }
        })
    }

    private fun setCuisineList(cuisineList: List<Cuisine>?) {
        _binding.cuisineProgressBar.gone()
        _binding.cuisineRecyclerView.show()

        cuisineAdapter.setData(cuisineList)
        _binding.cuisineRecyclerView.adapter = cuisineAdapter
    }

    private fun setRestaurant(restaurantList: List<Restaurant>?) {
        _binding.restaurantProgressBar.gone()
        _binding.restaurantRecyclerView.show()

        restaurantAdapter.setData(restaurantList)
        _binding.restaurantRecyclerView.adapter = restaurantAdapter
    }

}