package com.teamsparta.backoffice.domain.user.model

import com.teamsparta.backoffice.domain.user.dto.users.ModifyUserRequest
import com.teamsparta.backoffice.infra.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "auth_user")

class AuthUser(
        @Column(name = "email")
        val email: String,
        @Column(name = "nickname")
        var nickname: String?,
        @Column(name = "phoneNumber")
        var phoneNumber: String?,
        @Enumerated(EnumType.STRING)
        @Column(name = "role")
        val role: UserRole,
        @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "auth_account_id")
        var authAccount: AuthAccount,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun modifyAuthUser(request: ModifyUserRequest) {
        nickname = request.nickname
        phoneNumber = request.phoneNumber

    }

}
