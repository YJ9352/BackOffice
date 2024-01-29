package com.teamsparta.backoffice.domain.order.dto

data class OrderMenuResponse (
    val menuId:Long,
    val name:String,
    val price:Int,
    val quantity:Int
)