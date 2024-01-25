package com.teamsparta.backoffice.domain.order.controller

import com.teamsparta.backoffice.domain.order.dto.ChangeOrderStatusRequest
import com.teamsparta.backoffice.domain.order.dto.CreateOrderRequest
import com.teamsparta.backoffice.domain.order.dto.OrderResponse
import com.teamsparta.backoffice.domain.order.service.OrderService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService
) {

    @PostMapping
    fun createOrder(
        @RequestBody createOrderRequest: CreateOrderRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<OrderResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.createOrder(userPrincipal.id, createOrderRequest))
    }

    @GetMapping
    fun getOrderList(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestParam status: String,
        @RequestParam storeId: Long
    ): ResponseEntity<List<OrderResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderList(userPrincipal.id, status, storeId))
    }

    @PatchMapping
    fun changeOrderStatus(@RequestBody changeOrderStatusRequest: ChangeOrderStatusRequest)
            : ResponseEntity<OrderResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.changeOrderStatus(changeOrderStatusRequest))

    }


}