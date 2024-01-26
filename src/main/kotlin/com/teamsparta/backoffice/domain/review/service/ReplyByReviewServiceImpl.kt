package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.AddReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.UpdateReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse
import com.teamsparta.backoffice.domain.review.model.ReplyByReview
import com.teamsparta.backoffice.domain.review.model.toResponse
import com.teamsparta.backoffice.domain.review.repository.ReplyByReviewRepository
import com.teamsparta.backoffice.domain.review.repository.ReviewRepository
import com.teamsparta.backoffice.domain.store.repository.StoreRepository
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReplyByReviewServiceImpl(
    private val storeRepository: StoreRepository,
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository,
    private val replyByReviewRepository: ReplyByReviewRepository
):ReplyByReviewService {

    @Transactional
    override fun addReplyByReview(
        storeId: Long,
        reviewId: Long,
        userId: Long,
        request: AddReplyByReviewRequest,
    ): ReviewResponse {
        val review = reviewRepository.findByStoreIdAndId(storeId, reviewId)
            ?: throw ModelNotFoundException("review", reviewId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)

        val reply = ReplyByReview(
            content = request.content,
            user = user,
            review = review
        )
        review.replies = reply

        return reviewRepository.save(review).toResponse()
    }

    @Transactional
    override fun updateReplyByReview(
        storeId: Long,
        reviewId: Long,
        replyId: Long,
        request: UpdateReplyByReviewRequest,
    ): ReplyByReviewResponse {

        val reply = replyByReviewRepository.findByIdOrNull(replyId)
            ?: throw ModelNotFoundException("reply", replyId)

        reply.content = request.content

        return replyByReviewRepository.save(reply).toResponse()
    }

    override fun deleteReplyByReview(storeId: Long, reviewId: Long, replyId: Long) {
        val reply = replyByReviewRepository.findByIdOrNull(replyId)
            ?: throw ModelNotFoundException("reply", replyId)

        replyByReviewRepository.deleteById(replyId)
    }
}