package com.teamsparta.backoffice.domain.order.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.teamsparta.backoffice.domain.order.model.Order
import com.teamsparta.backoffice.domain.order.model.OrderStatus
import com.teamsparta.backoffice.domain.order.model.QOrder
import com.teamsparta.backoffice.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl : QueryDslSupport(), CustomOrderRepository {

    private val order = QOrder.order

    /*
    userId = Long, status = null, storeId = null
    유저가 본인의 주문을 확인한다.

    userId = Long, status = null, storeId = Long
    가게 주인이 storeId인 가게의 모든 상태의 주문을 확인한다.

    userId = Long, status = String, storeId = Long
    가게 주인이 storeId인 가게의 어떤 상태의 주문을 확인한다.
    */
    override fun selectOrderList(userId: Long, status: OrderStatus?, storeId: Long?): List<Order> {
        return queryFactory.selectFrom(order)
            .where(
                userIdContains(userId, storeId),
                storeIdContains(storeId),
                statusContains(storeId, status)
            )
            .fetch()
    }

    private fun userIdContains(userId: Long, storeId: Long?): BooleanExpression? {
        return storeId?.let { order.user.id.eq(userId) }
    }

    private fun storeIdContains(storeId: Long?): BooleanExpression? {
        return storeId?.let { order.store.id.eq(storeId) }
    }

    private fun statusContains(storeId: Long?, status: OrderStatus?): BooleanExpression? {
        if (storeId == null) return null
        return status?.let { order.status.eq(status) }
    }

}