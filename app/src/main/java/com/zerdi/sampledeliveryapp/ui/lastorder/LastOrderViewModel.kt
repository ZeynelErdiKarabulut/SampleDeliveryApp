package com.zerdi.sampledeliveryapp.ui.lastorder

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LastOrderViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getLastOrders() = apiRepository.getLastOrders()
    fun rateOrder(vote: Float, mealId: Int, cartId: Int) =
        apiRepository.rateOrder(mealId, vote, cartId)

}