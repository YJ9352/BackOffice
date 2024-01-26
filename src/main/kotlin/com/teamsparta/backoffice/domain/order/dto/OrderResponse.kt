package com.teamsparta.backoffice.domain.order.dto

import java.time.LocalDateTime

data class OrderResponse(
    val orderId: Long,
    val userId: Long,
    val menuList: List<OrderMenuResponse>,
    val totalPrice: Int,
    val phone: String,
    val address: String,
    val paymentTime: LocalDateTime,
    val status:String
)
