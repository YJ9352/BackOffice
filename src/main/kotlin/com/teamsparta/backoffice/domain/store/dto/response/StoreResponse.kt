package com.teamsparta.backoffice.domain.store.dto.response

import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse

data class StoreResponse(
    val storeId: Long,
    val name: String,
    val profileImgUrl: String,
    val category: String,
    val address: String,
    val phone: String,
    val description: String,
    // 리뷰 추가
    val reviews: List<ReviewResponse>
)
