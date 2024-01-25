package com.teamsparta.backoffice.domain.order.service

import com.teamsparta.backoffice.domain.order.dto.ChangeOrderStatusRequest
import com.teamsparta.backoffice.domain.order.dto.CreateOrderRequest
import com.teamsparta.backoffice.domain.order.dto.OrderResponse
import com.teamsparta.backoffice.domain.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository
) : OrderService {

    override fun getOrderList(userId: Long, status: String, storeId: Long): List<OrderResponse> {
        TODO("Not yet implemented")
    }

    override fun createOrder(userId: Long, createOrderRequest: CreateOrderRequest): OrderResponse {
        TODO("Not yet implemented")
    }

    override fun changeOrderStatus(changeOrderStatusRequest: ChangeOrderStatusRequest): OrderResponse {
        TODO("Not yet implemented")
    }

}