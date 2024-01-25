package com.teamsparta.backoffice.domain.order.repository

import com.teamsparta.backoffice.domain.order.model.QOrder
import com.teamsparta.backoffice.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl : QueryDslSupport(), CustomOrderRepository {

    private val order = QOrder.order


}