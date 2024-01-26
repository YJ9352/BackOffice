package com.teamsparta.backoffice.domain.store.dto.response

data class StoreResponse(
        val storeId: Long,
        val name: String,
        val profileImgUrl: String,
        val category: String,
        val address: String,
        val phone: String,
        val description: String
)
