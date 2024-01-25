package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.AddReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse

interface ReplyByReviewService {

    fun AddReplyByReview(
        storeId: Long,
        reviewId: Long,
        userId: Long,
        request: AddReplyByReviewRequest): ReplyByReviewResponse
}