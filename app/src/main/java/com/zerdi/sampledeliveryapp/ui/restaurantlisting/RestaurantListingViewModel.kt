package com.zerdi.sampledeliveryapp.ui.restaurantlisting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantListingViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurantsByCuisine(cuisineId: Int) = apiRepository.getRestaurantsByCuisine(cuisineId)

}