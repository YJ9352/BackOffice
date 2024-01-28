package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.exception.CustomException
import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.AddReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse
import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.UpdateReplyByReviewRequest
import com.teamsparta.backoffice.domain.review.model.ReplyByReview
import com.teamsparta.backoffice.domain.review.model.toResponse
import com.teamsparta.backoffice.domain.review.repository.ReplyByReviewRepository
import com.teamsparta.backoffice.domain.review.repository.ReviewRepository
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReplyByReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository,
    private val replyByReviewRepository: ReplyByReviewRepository
) : ReplyByReviewService {

    @Transactional
    override fun addReplyByReview(
        storeId: Long,
        reviewId: Long,
        userId: Long,
        request: AddReplyByReviewRequest,
    ): ReplyByReviewResponse {
        val review = reviewRepository.findByStoreIdAndId(storeId, reviewId)
            ?: throw ModelNotFoundException("review", reviewId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("user", userId)
        if (review.replies != null) {
            throw CustomException("이미 답글이 달린 리뷰 입니다.")
        }

        val reply = ReplyByReview(
            content = request.content,
            user = user,
            review = review
        )

        return replyByReviewRepository.save(reply).toResponse()
    }

    @Transactional
    override fun updateReplyByReview(
        storeId: Long,
        reviewId: Long,
        userId: Long,
        replyId: Long,
        request: UpdateReplyByReviewRequest,
    ): ReplyByReviewResponse {
        val review = reviewRepository.findByIdOrNull(reviewId)
            ?: throw ModelNotFoundException("review", reviewId)
        val reply = replyByReviewRepository.findByIdOrNull(replyId)
            ?: throw ModelNotFoundException("reply", replyId)

        reply.content = request.content

        return replyByReviewRepository.save(reply).toResponse()
    }

    @Transactional
    override fun deleteReplyByReview(storeId: Long, reviewId: Long, replyId: Long) {
        val reply = replyByReviewRepository.findByIdOrNull(replyId)
            ?: throw ModelNotFoundException("reply", replyId)

        replyByReviewRepository.delete(reply)
    }
}