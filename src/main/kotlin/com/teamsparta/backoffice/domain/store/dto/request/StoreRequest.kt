package com.teamsparta.backoffice.domain.store.dto.request

data class StoreRequest(
        val name: String,
        val profileImgUrl: String,
        val category: String,
        val address: String,
        val phone: String,
        val description: String
)
