package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.AccountResponse

interface AccountService {
    fun getMyAccount (id:Long) : AccountResponse
}