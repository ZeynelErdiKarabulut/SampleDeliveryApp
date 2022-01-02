package com.zerdi.sampledeliveryapp.ui.favorite

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getFavoriteRestaurantList() = apiRepository.getFavoriteRestaurantList()

}