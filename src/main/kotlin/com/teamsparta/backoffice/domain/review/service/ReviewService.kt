package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.review.dto.AddReviewRequest
import com.teamsparta.backoffice.domain.review.dto.ReviewResponse
import com.teamsparta.backoffice.domain.review.dto.UpdateReviewRequest

interface ReviewService {

    fun getReviewByStore(storeId: Long): List<ReviewResponse>

    fun addReview(storeId:Long, userId: Long, request: AddReviewRequest) : ReviewResponse

    fun updateReview(storeId: Long, userId: Long, reviewId: Long, request: UpdateReviewRequest): ReviewResponse
}