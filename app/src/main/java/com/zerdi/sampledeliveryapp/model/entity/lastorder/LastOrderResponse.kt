package com.zerdi.sampledeliveryapp.model.entity.lastorder


import com.zerdi.sampledeliveryapp.model.entity.basket.Basket
import com.google.gson.annotations.SerializedName

data class LastOrderResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val orderDetail: List<Basket>,
    @SerializedName("success")
    val success: Boolean
)