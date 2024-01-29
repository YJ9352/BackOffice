package com.teamsparta.backoffice.domain.order.service

import com.teamsparta.backoffice.domain.cart.model.CartMenu
import com.teamsparta.backoffice.domain.cart.repository.CartMenuRepository
import com.teamsparta.backoffice.domain.cart.repository.CartRepository
import com.teamsparta.backoffice.domain.exception.CustomException
import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.order.dto.ChangeOrderStatusRequest
import com.teamsparta.backoffice.domain.order.dto.CreateOrderRequest
import com.teamsparta.backoffice.domain.order.dto.OrderMenuResponse
import com.teamsparta.backoffice.domain.order.dto.OrderResponse
import com.teamsparta.backoffice.domain.order.model.Order
import com.teamsparta.backoffice.domain.order.model.OrderMenu
import com.teamsparta.backoffice.domain.order.model.OrderStatus
import com.teamsparta.backoffice.domain.order.repository.OrderMenuRepository
import com.teamsparta.backoffice.domain.order.repository.OrderRepository
import com.teamsparta.backoffice.domain.user.repository.AccountRepository
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val accountRepository: AccountRepository,
    private val cartRepository: CartRepository,
    private val cartMenuRepository: CartMenuRepository,
    private val orderMenuRepository: OrderMenuRepository
) : OrderService {

    override fun getOrderList(userId: Long, status: String?, storeId: Long?): List<OrderResponse> {

        return orderRepository
            .selectOrderList(userId, status?.let { OrderStatus.valueOf(it) }, storeId)
            .map { getOrderResponse(it) }
    }


    @Transactional
    override fun createOrder(userId: Long, createOrderRequest: CreateOrderRequest): OrderResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
        val cart = cartRepository.findByUserId(userId) ?: throw ModelNotFoundException("cart", userId)
        val account = user.account
        val cartMenuList =
            cartMenuRepository.findByCartId(cart.id!!) ?: throw ModelNotFoundException("cartMenu", cart.id)
        val totalPrice = cartMenuList.fold(0) { acc, cartMenu -> acc + (cartMenu.menu.price * cartMenu.quantity) }

        if (account.money < totalPrice) {
            throw CustomException("잔고가 부족합니다.")
        }
        account.money = account.money - totalPrice
        accountRepository.save(account)
        val order = orderRepository.save(
            Order(
                status = OrderStatus.CONFIRM_WAIT,
                totalPay = totalPrice,
                phone = createOrderRequest.phone,
                address = createOrderRequest.address,
                user = user,
                store = cart.store,
                paymentTime = LocalDateTime.now()
            )
        )

        cartMenuList.map { orderMenuRepository.save(it.toOrderMenu(order)) }
        cartMenuRepository.deleteByCartId(cart.id!!)
        return getOrderResponse(order)
    }

    @Transactional
    override fun changeOrderStatus(
        userId: Long,
        orderId: Long,
        changeOrderStatusRequest: ChangeOrderStatusRequest
    ): OrderResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
        val order = orderRepository.findByIdOrNull(orderId) ?: throw ModelNotFoundException("order", orderId)
        val statusEnum = OrderStatus.valueOf(changeOrderStatusRequest.status)

        if (order.user.id != user.id && order.store.user.id != user.id) {
            throw AccessDeniedException("주문 수정 권한이 없음")
        }

        if(arrayOf(OrderStatus.STORE_CANCEL,OrderStatus.CUSTOMER_CANCEL).contains(statusEnum)){
            val account = user.account
            account.money = account.money + order.totalPay
            accountRepository.save(account)
        }

        order.status = statusEnum
        orderRepository.save(order)
        return getOrderResponse(order)
    }

    fun getOrderResponse(order: Order): OrderResponse {
        return OrderResponse(
            orderId = order.id!!,
            userId = order.user.id!!,
            totalPrice = order.totalPay,
            phone = order.phone,
            address = order.address,
            paymentTime = order.paymentTime,
            menuList = orderMenuRepository.findByOrderId(order.id!!).map { it.toResponse() },
            status = order.status.name
        )
    }

    fun OrderMenu.toResponse(): OrderMenuResponse {
        return OrderMenuResponse(
            menuId = menu.id!!,
            name = menu.name,
            price = menu.price,
            quantity = quantity
        )
    }

    fun CartMenu.toOrderMenu(order: Order): OrderMenu {
        return OrderMenu(
            menu = menu,
            order = order,
            quantity = quantity
        )
    }

}