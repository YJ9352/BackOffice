package com.teamsparta.backoffice.domain.order.model

enum class OrderStatus {
    CONFIRM_WAIT,
    COOKING,
    DELIVERING,
    FINISH,
    CUSTOMER_CANCEL,
    STORE_CANCEL
}