package com.teamsparta.backoffice.domain.review.repository

import com.teamsparta.backoffice.domain.review.model.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository: JpaRepository<Review, Long> {
    fun findByStoreIdAndId(storeId: Long, reviewId: Long): Review?

}