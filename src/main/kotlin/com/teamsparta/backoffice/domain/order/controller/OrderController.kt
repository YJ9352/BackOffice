package com.teamsparta.backoffice.domain.order.controller

import com.teamsparta.backoffice.domain.order.dto.ChangeOrderStatusRequest
import com.teamsparta.backoffice.domain.order.dto.CreateOrderRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController {

    @PostMapping
    fun createOrder(@RequestBody createOrderRequest: CreateOrderRequest){
        TODO()
    }

    @GetMapping
    fun getMyOrder(){
        TODO()
    }


    @PatchMapping
    fun changeOrderStatus(@RequestBody changeOrderStatusRequest: ChangeOrderStatusRequest){

    }


}