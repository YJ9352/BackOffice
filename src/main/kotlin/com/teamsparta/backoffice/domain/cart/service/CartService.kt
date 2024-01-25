package com.teamsparta.backoffice.domain.cart.service

import com.teamsparta.backoffice.domain.cart.dto.AddCartMenuRequest
import com.teamsparta.backoffice.domain.cart.dto.CartResponse

interface CartService {


    fun addCartMenu(userId: Long, addCartMenuRequest: AddCartMenuRequest): CartResponse
    fun getCartByUserId(userId: Long): CartResponse
    fun deleteCartByUserId(userId: Long)
    fun deleteCartMenu(userId: Long, cartMenuId: Long)
}