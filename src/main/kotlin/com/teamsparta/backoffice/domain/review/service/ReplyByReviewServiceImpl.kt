package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.AddReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ReplyByReviewServiceImpl:ReplyByReviewService {

    @Transactional
    override fun AddReplyByReview(
        storeId: Long,
        reviewId: Long,
        userId: Long,
        request: AddReplyByReviewRequest,
    ): ReplyByReviewResponse {
        TODO("Not yet implemented")
    }
}