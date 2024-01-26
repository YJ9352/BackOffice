package com.teamsparta.backoffice.domain.review.model

import com.teamsparta.backoffice.domain.review.dto.reviewDto.ReviewResponse
import com.teamsparta.backoffice.domain.store.model.Store
import com.teamsparta.backoffice.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "review")
class Review(

    @Column(name = "review_content")
    var content: String,

    @Column(name = "review_rating")
    var rating: Int,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @ManyToOne
    @JoinColumn(name = "store_id")
    val store: Store,

    @OneToOne(mappedBy = "review", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var replies: ReplyByReview?


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
        rating = rating,
        reply = replies?.let { it.toResponse() }
    )
}