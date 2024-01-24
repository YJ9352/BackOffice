package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.AccountResponse
import com.teamsparta.backoffice.domain.user.model.toResponse
import com.teamsparta.backoffice.domain.user.repository.AccountRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
        private val accountRepository: AccountRepository
) : AccountService {
    override fun getMyAccount(id: Long?): AccountResponse {
        val account = accountRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Invalid role")
        return account.toResponse()
    }

    @Transactional
    override fun modifyMyAccount(id: Long?, request: AccountRequest): AccountResponse {
        val account = accountRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Invalid role")
        account.modifyAccount(request)
        return accountRepository.save(account).toResponse()

    }
}