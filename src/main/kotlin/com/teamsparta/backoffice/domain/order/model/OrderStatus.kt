package com.teamsparta.backoffice.domain.order.model

enum class OrderStatus {
    CONFIRM_WAIT,
    COOKING,
    CUSTOMER_CANCEL,
    STORE_CANCEL,
    DELIVERING,
    FINISH
}