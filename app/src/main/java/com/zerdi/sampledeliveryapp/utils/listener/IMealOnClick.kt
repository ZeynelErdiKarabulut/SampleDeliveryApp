package com.zerdi.sampledeliveryapp.utils.listener

import com.zerdi.sampledeliveryapp.model.entity.meal.Meal

interface IMealOnClick {
    fun onClick(meal: Meal)
    fun onClickBasket(meal: Meal)
}