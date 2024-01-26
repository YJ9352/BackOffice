package com.teamsparta.backoffice.domain.oauth.service

import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.oauth.model.toResponse
import com.teamsparta.backoffice.domain.oauth.repository.OauthAccountRepository
import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.account.AccountResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OauthAccountServiceImpl(
        private val oauthAccountRepository: OauthAccountRepository
) : OauthAccountService {
    override fun getMyAccount(id: Long?): AccountResponse {
        val account = oauthAccountRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Account", id)
        return account.toResponse()
    }

    override fun modifyMyAccount(id: Long?, request: AccountRequest): AccountResponse {
        val account = oauthAccountRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Account", id)
        account.modifyAccount(request)
        return oauthAccountRepository.save(account).toResponse()
    }

}
