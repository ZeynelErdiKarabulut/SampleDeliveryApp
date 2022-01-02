package com.zerdi.sampledeliveryapp.utils.listener

import com.zerdi.sampledeliveryapp.model.entity.basket.CartData

interface ICartOnClick {
    fun onClick(cart: CartData)
}