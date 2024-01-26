package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.AddReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.UpdateReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse

interface ReplyByReviewService {

    fun addReplyByReview(
        storeId: Long,
        reviewId: Long,
        userId: Long,
        request: AddReplyByReviewRequest
    ): ReviewResponse

    fun updateReplyByReview(
        storeId: Long,
        reviewId: Long,
        replyId: Long,
        request: UpdateReplyByReviewRequest
    ): ReplyByReviewResponse

    fun deleteReplyByReview(
        storeId: Long,
        reviewId: Long,
        replyId: Long
    )
}