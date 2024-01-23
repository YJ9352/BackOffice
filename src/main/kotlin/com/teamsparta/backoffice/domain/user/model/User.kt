package com.teamsparta.backoffice.domain.user.model

import com.teamsparta.backoffice.domain.user.dto.ModifyUserRequest
import com.teamsparta.backoffice.domain.user.dto.SearchUserResponse
import com.teamsparta.backoffice.domain.user.dto.UserResponse
import com.teamsparta.backoffice.infra.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "app_user")

class User(
        @Column(name = "email")
        val email: String,

        @Column(name = "password")
        var password: String,

        @Column(name = "nickname")
        var nickname: String,
        @Column(name = "phoneNumber")
        var phoneNumber : String,
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
        ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun modifyUser(request: ModifyUserRequest) {
        nickname = request.nickname
        password = request.password
        phoneNumber = request.phoneNumber
        balance = request.balance
    }
}

fun User.toResponseMail(): UserResponse {
    return UserResponse(
            email = email
    )
}
fun User.toResponse(): SearchUserResponse {
    return SearchUserResponse(
            email = email,
            nickname = nickname,
            role = role.name,
            phoneNumber = phoneNumber,
            balance = balance

    )
}