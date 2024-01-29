package com.teamsparta.backoffice.domain.review.service

import com.teamsparta.backoffice.domain.exception.CustomException
import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.review.dto.reviewDto.AddReviewRequest
import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse
import com.teamsparta.backoffice.domain.review.dto.reviewDto.UpdateReviewRequest
import com.teamsparta.backoffice.domain.review.model.Review
import com.teamsparta.backoffice.domain.review.model.toResponse
import com.teamsparta.backoffice.domain.review.repository.ReviewRepository
import com.teamsparta.backoffice.domain.store.repository.StoreRepository
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository,
    private val storeRepository: StoreRepository
) : ReviewService {
    override fun getReviewByStore(storeId: Long): List<ReviewResponse> {
        val store = storeRepository.findByIdOrNull(storeId)
            ?: throw ModelNotFoundException("store", storeId)

        val review = reviewRepository.findAllByStore(store)

        return review.map { review ->
            ReviewResponse(
                id = review.id!!,
                content = review.content,
                rating = review.rating,
                nickname = review.user.nickname,
                reply = review.replies?.let { it.toResponse() }

            )
        }
    }

    @Transactional
    override fun addReview(
        storeId: Long,
        userId: Long,
        request: AddReviewRequest,
    ): ReviewResponse {

        val store = storeRepository.findByIdOrNull(storeId) ?: throw ModelNotFoundException("store", storeId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("user", userId)
        if (store.user.id == user.id) {
            throw CustomException("자기 자신의 가게에 리뷰를 남길 수 없음.")
        }

        val review = Review(
            content = request.content,
            rating = request.rating,
            store = store,
            user = user,
            replies = null
        )
        return reviewRepository.save(review).toResponse()
    }

    @Transactional
    override fun updateReview(
        storeId: Long,
        userId: Long,
        reviewId: Long,
        request: UpdateReviewRequest,
    ): ReviewResponse {
        val review = reviewRepository.findByIdOrNull(reviewId)
            ?: throw ModelNotFoundException("review", reviewId)

        review.content = request.content

        return reviewRepository.save(review).toResponse()
    }

    @Transactional
    override fun deleteReview(
        storeId: Long,
        userId: Long,
        reviewId: Long,
    ) {
        val review = reviewRepository.findByStoreIdAndId(storeId, reviewId)
            ?: throw ModelNotFoundException("review", reviewId)

        reviewRepository.delete(review)
    }
}

