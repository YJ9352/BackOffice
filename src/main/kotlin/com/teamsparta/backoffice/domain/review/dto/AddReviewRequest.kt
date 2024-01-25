package com.teamsparta.backoffice.domain.review.dto

data class AddReviewRequest(
    val content: String,
    val rating: Int
)
