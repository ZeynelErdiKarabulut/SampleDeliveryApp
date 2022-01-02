package com.zerdi.sampledeliveryapp.utils.listener

interface IOrderRatingOnClick {
    fun onClick(vote: Float, mealId: Int, cartId : Int)
}