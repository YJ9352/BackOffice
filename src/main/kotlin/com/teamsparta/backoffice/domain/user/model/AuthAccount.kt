package com.teamsparta.backoffice.domain.user.model

import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import jakarta.persistence.*

@Entity
@Table(name = "auth_account")
class AuthAccount(

        @Column(name = "money")
        var money: Int = 0,

        ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    fun modifyAuthAccount(request: AccountRequest) {
        money = request.money

    }

}