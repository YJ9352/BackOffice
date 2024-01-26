package com.teamsparta.backoffice.domain.order.controller

import com.teamsparta.backoffice.domain.order.dto.ChangeOrderStatusRequest
import com.teamsparta.backoffice.domain.order.dto.CreateOrderRequest
import com.teamsparta.backoffice.domain.order.dto.OrderResponse
import com.teamsparta.backoffice.domain.order.service.OrderService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(summary = "Sets a price for a chosen car", description = "Returns 202 if successful")
    fun getOrderList(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestParam(required = false) status: String,
        @RequestParam(required = false) storeId: Long
    ): ResponseEntity<List<OrderResponse>> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.getOrderList(userPrincipal.id, status, storeId))
    }

    @PatchMapping("/{orderId}")
    fun changeOrderStatus(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody changeOrderStatusRequest: ChangeOrderStatusRequest,
        @PathVariable orderId: Long
    ): ResponseEntity<OrderResponse> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(orderService.changeOrderStatus(userPrincipal.id, orderId, changeOrderStatusRequest))
    }


}