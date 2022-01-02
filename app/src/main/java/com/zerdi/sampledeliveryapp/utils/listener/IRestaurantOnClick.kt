package com.zerdi.sampledeliveryapp.utils.listener

import com.zerdi.sampledeliveryapp.model.entity.restaurant.Restaurant

interface IRestaurantOnClick {
    fun onClick(restaurant: Restaurant)
}