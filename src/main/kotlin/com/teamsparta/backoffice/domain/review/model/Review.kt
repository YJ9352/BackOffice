package com.teamsparta.backoffice.domain.review.model

import com.teamsparta.backoffice.domain.review.dto.ReviewResponse
import com.teamsparta.backoffice.domain.store.model.Store
import com.teamsparta.backoffice.domain.user.model.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "review")
class Review(

    @Column(name = "review_content")
    var content: String,

    @Column(name = "review_rating")
    var rating: Int,

    @Column(name = "review_created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "review_updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

//    @ManyToOne
//    @JoinColumn(name = "store_id", nullable = false)
//    val store: Store

    @Column(name = "store_id")
    val store: Long

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Review.toResponse(): ReviewResponse {
    return ReviewResponse(
        id = id!!,
        content = content,
        nickname = user.nickname,
        rating = rating
    )
}