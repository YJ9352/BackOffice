package com.teamsparta.backoffice.domain.order.repository

import com.teamsparta.backoffice.domain.order.model.Order
import com.teamsparta.backoffice.domain.order.model.OrderStatus

interface CustomOrderRepository {

    fun selectOrderList(userId: Long, status: OrderStatus?, storeId: Long?): List<Order>

}