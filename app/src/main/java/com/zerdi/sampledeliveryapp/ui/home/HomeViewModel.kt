package com.zerdi.sampledeliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.cuisine.Cuisine
import com.zerdi.sampledeliveryapp.model.entity.restaurant.Restaurant
import com.zerdi.sampledeliveryapp.model.entity.cuisine.CuisineListResponse
import com.zerdi.sampledeliveryapp.model.entity.restaurant.RestaurantResponse
import com.zerdi.sampledeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
)  : ViewModel() {

    var restaurantList: List<Restaurant>? = null
    var cuisineList: List<Cuisine>? = null

    fun getRestaurantList(): LiveData<Resource<RestaurantResponse>> {
        return apiRepository.getRestaurantList()
    }

    fun getCuisineList(): LiveData<Resource<CuisineListResponse>> {
        return apiRepository.getCuisineList()
    }

    fun getUserImage() = apiRepository.getUserImage()

    fun searchTextOnRestaurantList(text: String?): List<Restaurant>? {
        if (text.isNullOrEmpty())
            return restaurantList

        val filterList: MutableList<Restaurant> = mutableListOf()
        restaurantList?.forEach { restaurant ->
            if (restaurant.name.contains(text, true))
                filterList.add(restaurant)
        }
        return filterList
    }

    fun searchTextOnCuisineList(text: String?): List<Cuisine>? {
        if (text.isNullOrEmpty())
            return cuisineList

        val filterList: MutableList<Cuisine> = mutableListOf()
        cuisineList?.forEach { cuisine ->
            if (cuisine.name.contains(text, true))
                filterList.add(cuisine)
        }
        return filterList
    }

}