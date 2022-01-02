package com.zerdi.sampledeliveryapp.utils.listener

import com.zerdi.sampledeliveryapp.model.entity.cuisine.Cuisine

interface ICuisineOnClick {
    fun onClick(cuisine: Cuisine)
}