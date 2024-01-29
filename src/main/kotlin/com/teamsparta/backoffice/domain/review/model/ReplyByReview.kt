package com.teamsparta.backoffice.domain.review.model

import com.teamsparta.backoffice.domain.review.dto.replyByReviewDto.ReplyByReviewResponse
import com.teamsparta.backoffice.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "review_reply")
class ReplyByReview(
    @OneToOne
    @JoinColumn(name = "review_id")
    var review: Review,

    @Column(name = "review_reply_content")
    var content: String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun ReplyByReview.toResponse() : ReplyByReviewResponse {
    return ReplyByReviewResponse(
        id = id!!,
        content = content
    )
}