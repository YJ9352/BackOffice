package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.review.dto.reviewDto.AddReviewRequest
import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse
import com.teamsparta.backoffice.domain.review.dto.reviewDto.UpdateReviewRequest

interface ReviewService {

    fun getReviewByStore(storeId: Long): List<ReviewResponse>

    fun addReview(
        storeId:Long,
        userId: Long,
        request: AddReviewRequest
    ) : ReviewResponse

    fun updateReview(
        storeId: Long,
        userId: Long,
        reviewId: Long,
        request: UpdateReviewRequest
    ): ReviewResponse

    fun deleteReview(
        storeId: Long,
        userId: Long,
        reviewId: Long)

}