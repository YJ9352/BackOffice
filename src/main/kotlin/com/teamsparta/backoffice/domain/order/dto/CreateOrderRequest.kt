package com.teamsparta.backoffice.domain.order.dto

data class CreateOrderRequest(
    val phone: String,
    val address: String
)
