package com.teamsparta.backoffice.domain.oauth.model

import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.account.AccountResponse
import jakarta.persistence.*

@Entity
@Table(name = "oauth_account")
class OauthAccount(
        @Column(name = "money")
        var money: Int = 0,

        ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    fun modifyAccount(request: AccountRequest) {
        money = request.money

    }
}

fun OauthAccount.toResponse(): AccountResponse {
    return AccountResponse(
            money = money
    )
}