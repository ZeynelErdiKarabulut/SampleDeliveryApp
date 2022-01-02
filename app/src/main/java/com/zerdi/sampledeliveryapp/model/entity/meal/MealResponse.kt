package com.zerdi.sampledeliveryapp.model.entity.meal

import com.google.gson.annotations.SerializedName

data class MealResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val mealList: List<Meal>,
    @SerializedName("success")
    val success: Boolean
)