package com.teamsparta.backoffice.domain.store.dto.response

data class UserStoreResponse(
    val storeId: Long,
    val name: String,
    val profileImgUrl: String,
    val category: String,
    val address: String,
    val phone: String,
    val description: String,
    val status: String,
    // 이후 별점 또는 좋아요 추가
)
