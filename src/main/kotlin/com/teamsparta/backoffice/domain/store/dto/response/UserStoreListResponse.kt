package com.teamsparta.backoffice.domain.store.dto.response

data class UserStoreListResponse(
    val storeId: Long,
    val name: String,
    val profileImgUrl: String,
    // 이후 별점 또는 좋아요 추가
)
