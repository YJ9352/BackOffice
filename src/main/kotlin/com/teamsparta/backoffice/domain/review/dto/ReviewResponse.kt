package com.teamsparta.backoffice.domain.review.dto


data class ReviewResponse(
    val id: Long,
    val content: String,
    val rating: Int,
    val nickname: String,

)
