package com.zerdi.sampledeliveryapp.model.entity.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String
)