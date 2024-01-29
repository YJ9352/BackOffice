package com.teamsparta.backoffice.domain.cart.dto

data class AddCartMenuRequest(
        val storeId: Long,
        val menuId: Long,
        val count: Int
)