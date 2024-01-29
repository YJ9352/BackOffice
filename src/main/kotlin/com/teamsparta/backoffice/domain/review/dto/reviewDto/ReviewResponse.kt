package com.teamsparta.backoffice.domain.review.dto.reviewDto

import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse


data class ReviewResponse(
    val id: Long,
    val content: String,
    val rating: Int,
    val nickname: String,
    val reply: ReplyByReviewResponse?
)
