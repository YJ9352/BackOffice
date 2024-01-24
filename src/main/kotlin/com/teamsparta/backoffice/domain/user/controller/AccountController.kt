package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.AccountResponse
import com.teamsparta.backoffice.domain.user.service.AccountService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/accounts")
@RestController
class AccountController(
        val accountService: AccountService
) {
    //1. 계좌 조회하기
    @GetMapping("/{accountId}")
    //인가 : 로그인한 사람의 id와 계좌 id가 동일해야 조회 가능
    @PreAuthorize("#userPrincipal.id == #accountId")
    fun getMyAccount(
            @PathVariable accountId: Long,
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<AccountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(accountId))
    }
    //2. 입금하기
    @PutMapping("/deposit/{accountId}")
    @PreAuthorize("#userPrincipal.id == #accountId")
    fun modifyMyAccount(
            @PathVariable accountId: Long,
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @RequestBody accountRequest: AccountRequest
    ): ResponseEntity<AccountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.modifyMyAccount(accountId, accountRequest))
    }
}