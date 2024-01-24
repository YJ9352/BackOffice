package com.teamsparta.backoffice.domain.menu.dto.response

data class MenuResponse(
        val menuId: Long,
        val name: String,
        val imageUrl: String,
        val description: String,
        val price: Int,
        val status: String
)