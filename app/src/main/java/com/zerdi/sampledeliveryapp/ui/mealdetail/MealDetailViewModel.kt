package com.zerdi.sampledeliveryapp.ui.mealdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.basket.BasketRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getMealById(mealId: Int) = apiRepository.getMealById(mealId)

    fun addBasket(request: BasketRequest) = apiRepository.addBasket(request)
}