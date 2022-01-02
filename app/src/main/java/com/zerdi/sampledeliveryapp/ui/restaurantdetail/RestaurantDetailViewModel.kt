package com.zerdi.sampledeliveryapp.ui.restaurantdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.basket.BasketRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getRestaurantById(restaurantId: Int) = apiRepository.getRestaurantById(restaurantId)

    fun addFavoriteRestaurant(restaurantId: Int) = apiRepository.addFavoriteRestaurant(restaurantId)

    fun removeFavoriteRestaurant(restaurantId: Int) =
        apiRepository.removeFavoriteRestaurant(restaurantId)

    fun addBasket(request: BasketRequest) = apiRepository.addBasket(request)
}

