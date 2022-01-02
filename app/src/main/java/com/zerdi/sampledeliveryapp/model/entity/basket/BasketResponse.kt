package com.zerdi.sampledeliveryapp.model.entity.basket


import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("data")
    val basketData: Basket
)