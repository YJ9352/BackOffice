package com.teamsparta.backoffice.domain.review.controller

import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.AddReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse
import com.teamsparta.backoffice.domain.review.service.ReplyByReviewService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/stores/{storeId}/reviews")
@RestController
class ReviewReplyController(
    private val replyByReviewService: ReplyByReviewService,
) {

    @PostMapping("/{reviewId}")
    fun addReplyByReview(
        @PathVariable storeId: Long,
        @PathVariable reviewId: Long,
        @RequestBody addReviewReplyRequest: AddReplyByReviewRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<ReplyByReviewResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(replyByReviewService.AddReplyByReview(storeId, reviewId, userPrincipal.id, addReviewReplyRequest))
    }
}