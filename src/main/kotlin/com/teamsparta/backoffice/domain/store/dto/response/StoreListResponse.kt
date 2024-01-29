package com.teamsparta.backoffice.domain.store.dto.response

data class StoreListResponse(
        val storeId: Long,
        val name: String,
        val category: String,
        val address: String,
        val phone: String,
        val description: String
)