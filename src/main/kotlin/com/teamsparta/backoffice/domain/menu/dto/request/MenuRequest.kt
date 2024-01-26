package com.teamsparta.backoffice.domain.menu.dto.request

data class MenuRequest(
        val name: String,
        val imageUrl: String,
        val description: String,
        val price: Int,
        val status: String
)