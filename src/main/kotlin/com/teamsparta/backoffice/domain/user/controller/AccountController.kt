package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.AccountResponse
import com.teamsparta.backoffice.domain.user.service.AccountService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/accounts")
@RestController
class AccountController(
        val accountService: AccountService
) {
    //1. 계좌 조회하기
    @GetMapping
    //인가 : 로그인한 사람의 id와 계좌 id가 동일해야 조회 가능
    fun getMyAccount(
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<AccountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(userPrincipal.id))
    }
    //2. 입금하기
    @PutMapping("/deposit")
    fun modifyMyAccount(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @RequestBody accountRequest: AccountRequest
    ): ResponseEntity<AccountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.modifyMyAccount(userPrincipal.id, accountRequest))
    }
}