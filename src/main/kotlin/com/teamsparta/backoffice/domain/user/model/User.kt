package com.teamsparta.backoffice.domain.user.model

import com.teamsparta.backoffice.domain.user.dto.UserResponse
import com.teamsparta.backoffice.infra.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "app_user")

class User(
        @Column(name = "email")
        val email: String,

        @Column(name = "password")
        val password: String,

        @Column(name = "nickname")
        val nickname: String,
        @Column(name = "phoneNumber")
        val phoneNumber : String,
        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: UserRole,
        @Column(name = "balance")
        var balance : Int
        /*
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id")
        var account: Account?,
         */
        ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
            email = email
    )
}
