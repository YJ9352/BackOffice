package com.teamsparta.backoffice.domain.order.service

import com.teamsparta.backoffice.domain.order.dto.ChangeOrderStatusRequest
import com.teamsparta.backoffice.domain.order.dto.CreateOrderRequest
import com.teamsparta.backoffice.domain.order.dto.OrderResponse


interface OrderService {
    fun getOrderList(userId: Long, status: String, storeId: Long): List<OrderResponse>
    fun createOrder(id: Long, createOrderRequest: CreateOrderRequest): OrderResponse
    fun changeOrderStatus(changeOrderStatusRequest: ChangeOrderStatusRequest): OrderResponse
}