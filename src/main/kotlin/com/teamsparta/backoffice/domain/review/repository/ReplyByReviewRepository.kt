package com.teamsparta.backoffice.domain.review.repository

import com.teamsparta.backoffice.domain.review.model.ReplyByReview
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyByReviewRepository : JpaRepository<ReplyByReview, Long>