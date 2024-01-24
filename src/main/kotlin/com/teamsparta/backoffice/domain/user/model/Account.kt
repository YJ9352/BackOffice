package com.teamsparta.backoffice.domain.user.model

import com.teamsparta.backoffice.domain.user.dto.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.AccountResponse
import jakarta.persistence.*

@Entity
@Table(name = "account")
class Account(

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

fun Account.toResponse(): AccountResponse {
    return AccountResponse(
            money = money
    )
}
