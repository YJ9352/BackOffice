package com.teamsparta.backoffice.domain.order.dto

import java.awt.Menu
import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long,
    val userId: Long,
    val menuList: List<Menu>,
    val totalPrice: Int,
    val phone: String,
    val address: String,
    val orderPaymentTime: LocalDateTime
)
