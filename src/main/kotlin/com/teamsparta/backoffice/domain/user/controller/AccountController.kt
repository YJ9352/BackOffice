package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.AccountResponse
import com.teamsparta.backoffice.domain.user.service.AccountService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/accounts")
@RestController
class AccountController (
        val accountService: AccountService
) {

    @GetMapping("/{accountId}")
    @PreAuthorize("#userPrincipal.id == #accountId")
    fun getMyAccount(
            @PathVariable accountId : Long,
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ) : ResponseEntity<AccountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(accountId))
    }
}