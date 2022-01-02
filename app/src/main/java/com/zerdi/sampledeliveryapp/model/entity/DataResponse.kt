package com.zerdi.sampledeliveryapp.model.entity

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("data")
    val isSuccess: Boolean
)
