package com.zerdi.sampledeliveryapp.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.zerdi.sampledeliveryapp.model.ApiRepository
import com.zerdi.sampledeliveryapp.model.entity.DataResponse
import com.zerdi.sampledeliveryapp.model.entity.basket.BasketResponse
import com.zerdi.sampledeliveryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    private var apiRepository: ApiRepository
) : ViewModel() {

    fun getBasketItemList(): LiveData<Resource<BasketResponse>> {
        return apiRepository.getBasketItemList()
    }

    fun buyBasket(): LiveData<Resource<DataResponse>> {
        return apiRepository.buyBasket()
    }

    fun removeItemFromBasket(mealId: Int): LiveData<Resource<DataResponse>> {
        return apiRepository.removeItemFromBasket(mealId)
    }

}