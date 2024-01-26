package com.teamsparta.backoffice.domain.review.controller

import com.teamsparta.backoffice.domain.review.dto.reviewDto.AddReviewRequest
import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse
import com.teamsparta.backoffice.domain.review.dto.reviewDto.UpdateReviewRequest
import com.teamsparta.backoffice.domain.review.service.ReviewService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/stores/{storeId}/reviews")
@RestController
class ReviewController(
    private val reviewService: ReviewService,
) {

    @GetMapping
    fun getReviews(
        @PathVariable storeId: Long,
    ): ResponseEntity<List<ReviewResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewByStore(storeId))
    }

    @PostMapping
    fun addReview(
        @PathVariable storeId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody addReviewRequest: AddReviewRequest,
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.addReview(storeId, userPrincipal.id, addReviewRequest))
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable storeId: Long,
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody updateReviewRequest: UpdateReviewRequest,
    ): ResponseEntity<ReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.updateReview(storeId, userPrincipal.id, reviewId, updateReviewRequest))
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(
        @PathVariable storeId: Long,
        @PathVariable reviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        reviewService.deleteReview(storeId, userPrincipal.id, reviewId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}
