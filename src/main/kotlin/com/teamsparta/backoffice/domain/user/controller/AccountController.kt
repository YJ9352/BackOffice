package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.account.AccountResponse
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import com.teamsparta.backoffice.domain.user.service.AccountService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RequestMapping("/accounts")
@RestController
class AccountController(
        val accountService: AccountService,
        val userRepository: UserRepository
) {
    //1. 계좌 조회하기
    @GetMapping
    fun getMyAccount(
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<AccountResponse> {
        val user = userRepository.findByIdOrNull(userPrincipal.id)
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(user?.account?.id))
    }

    //2. 입금하기
    @PutMapping("/deposit")
    fun modifyMyAccount(
            @AuthenticationPrincipal userPrincipal: UserPrincipal,
            @RequestBody accountRequest: AccountRequest
    ): ResponseEntity<AccountResponse> {
        val user = userRepository.findByIdOrNull(userPrincipal.id)
        return ResponseEntity.status(HttpStatus.OK).body(accountService.modifyMyAccount(user?.account?.id, accountRequest))
    }
    @GetMapping
    fun getAuthAccount(
            @AuthenticationPrincipal  oAuth2User: OAuth2User
    ): ResponseEntity<AccountResponse> {
        val user = userRepository.findByEmail(oAuth2User.name)
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getMyAccount(user?.account?.id))
    }

    //2. 입금하기
    @PutMapping("/deposit")
    fun modifyAuthAccount(
            @AuthenticationPrincipal  oAuth2User: OAuth2User,
            @RequestBody accountRequest: AccountRequest
    ): ResponseEntity<AccountResponse> {
        val authUser = userRepository.findByEmail(oAuth2User.name)
        return ResponseEntity.status(HttpStatus.OK).body(accountService.modifyMyAccount(authUser?.account?.id, accountRequest))
    }
}