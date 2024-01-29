package com.teamsparta.backoffice.domain.oauth.model

import com.teamsparta.backoffice.domain.oauth.dto.GetOauthResponse
import com.teamsparta.backoffice.domain.oauth.dto.ModifyOauthRequest
import com.teamsparta.backoffice.domain.user.model.UserRole
import com.teamsparta.backoffice.infra.audit.BaseTimeEntity
import jakarta.persistence.*

@Entity
@Table(name = "oauth_user")
class OauthUser(
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
        @JoinColumn(name = "oauth_account_id")
        var account: OauthAccount
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    fun modifyUser(request: ModifyOauthRequest) {
        nickname = request.nickname
        phoneNumber = request.phoneNumber
    }

}

fun OauthUser.toResponse(): GetOauthResponse {
    return GetOauthResponse(
            email = email,
            nickname = nickname,
            role = role.name,
            phoneNumber = phoneNumber
    )

}