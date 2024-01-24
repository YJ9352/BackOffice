package com.teamsparta.backoffice.domain.user.repository

import com.teamsparta.backoffice.domain.user.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<Account, Long> {
}