package com.teamsparta.backoffice.domain.menu.dto.response

data class GetAllMenuResponse(
    val menuId: Long,
    val name: String,
    val price: Int,
    val imageUrl: String
)