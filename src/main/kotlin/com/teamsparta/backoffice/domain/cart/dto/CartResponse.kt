package com.teamsparta.backoffice.domain.cart.dto

data class CartResponse(
        val cartId: Long,
        val userId: Long,
        val totalPrice: Int,
        val menuList: List<CartMenuResponse>
)