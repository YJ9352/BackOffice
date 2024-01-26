package com.teamsparta.backoffice.domain.order.repository

import com.teamsparta.backoffice.domain.order.model.OrderMenu
import org.springframework.data.jpa.repository.JpaRepository

interface OrderMenuRepository:JpaRepository<OrderMenu,Long> {
    fun findByOrderId(orderId:Long):List<OrderMenu>

}