package com.teamsparta.backoffice.domain.order.repository

import com.teamsparta.backoffice.domain.order.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order,Long>, CustomOrderRepository {


}