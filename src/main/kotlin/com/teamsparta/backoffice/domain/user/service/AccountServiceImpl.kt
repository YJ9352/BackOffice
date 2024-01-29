package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.account.AccountResponse
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
        val account = accountRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Account", id)
        return account.toResponse()
    }

    @Transactional
    override fun modifyMyAccount(id: Long?, request: AccountRequest): AccountResponse {
        val account = accountRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Account", id)
        account.modifyAccount(request)
        return accountRepository.save(account).toResponse()

    }
}