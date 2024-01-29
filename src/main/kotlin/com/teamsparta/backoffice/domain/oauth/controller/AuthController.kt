package com.teamsparta.backoffice.domain.oauth.controller

import com.teamsparta.backoffice.domain.oauth.dto.GetOauthResponse
import com.teamsparta.backoffice.domain.oauth.dto.ModifyOauthRequest
import com.teamsparta.backoffice.domain.oauth.dto.OauthLoginResponse
import com.teamsparta.backoffice.domain.oauth.repository.OauthUserRepository
import com.teamsparta.backoffice.domain.oauth.service.OauthAccountService
import com.teamsparta.backoffice.domain.oauth.service.OauthUserService
import com.teamsparta.backoffice.domain.user.dto.account.AccountRequest
import com.teamsparta.backoffice.domain.user.dto.account.AccountResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/google")
class AuthController(
        private val oauthUserService: OauthUserService,
        private val oauthUserRepository: OauthUserRepository,
        private val oauthAccountService: OauthAccountService
) {
    //1. 구글 로그인
    @PostMapping("/login")
    fun login(
            @AuthenticationPrincipal oAuth2User: OAuth2User,
    ): ResponseEntity<OauthLoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(oauthUserService.login(oAuth2User))
    }

    // 2. 내 정보 보기
    @GetMapping
    fun getMyInfo(
            @AuthenticationPrincipal oAuth2User: OAuth2User,
    ): ResponseEntity<GetOauthResponse> {
        val email = oAuth2User.attributes["email"] as String
        return ResponseEntity.status(HttpStatus.OK).body(oauthUserService.getMyInfo(email))

    }

    //3. 내 정보 수정하기
    @PatchMapping
    // 입력한 userId와 현재 로그인한 사람의 id가 같아야 정보 수정 가능
    fun modifyMyInfo(
            @RequestBody request: ModifyOauthRequest,
            @AuthenticationPrincipal oAuth2User: OAuth2User,
    ): ResponseEntity<GetOauthResponse> {
        val email = oAuth2User.attributes["email"] as String
        return ResponseEntity.status(HttpStatus.OK).body(oauthUserService.modifyMyInfo(email, request))

    }

    //4. 계좌 조회하기
    @GetMapping("/accounts")
    fun getMyAccount(
            @AuthenticationPrincipal oAuth2User: OAuth2User
    ): ResponseEntity<AccountResponse> {
        val oauthUser = oauthUserRepository.findByEmail(oAuth2User.attributes["email"] as String)
        return ResponseEntity.status(HttpStatus.OK).body(oauthAccountService.getMyAccount(oauthUser!!.account.id))

    }

    //5. 입금하기
    @PutMapping("/deposit")
    fun modifyMyAccount(
            @AuthenticationPrincipal oAuth2User: OAuth2User,
            @RequestBody request: AccountRequest
    ): ResponseEntity<AccountResponse> {
        val oauthUser = oauthUserRepository.findByEmail(oAuth2User.attributes["email"] as String)
        return ResponseEntity.status(HttpStatus.OK).body(oauthAccountService.modifyMyAccount(oauthUser!!.account.id, request))
    }
}