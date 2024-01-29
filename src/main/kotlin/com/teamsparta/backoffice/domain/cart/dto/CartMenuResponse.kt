package com.teamsparta.backoffice.domain.cart.dto

data class CartMenuResponse(
        val cartMenuId: Long,
        val menuId: Long,
        val name: String,
        val price: Int,
        val quantity: Int
)