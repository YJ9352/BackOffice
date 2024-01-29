package com.teamsparta.backoffice.domain.oauth.service

import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.account.AccountResponse


interface OauthAccountService {
    fun getMyAccount(id: Long?): AccountResponse
    fun modifyMyAccount(id: Long?, request: AccountRequest): AccountResponse


}