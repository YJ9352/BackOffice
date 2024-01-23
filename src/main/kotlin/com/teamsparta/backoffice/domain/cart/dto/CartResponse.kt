package com.teamsparta.backoffice.domain.cart.dto

data class CartResponse(
    val cartId: Long,
    val userId: Long,
    // Todo MenuList List<Menu>
    val menuList: List<Long>
)