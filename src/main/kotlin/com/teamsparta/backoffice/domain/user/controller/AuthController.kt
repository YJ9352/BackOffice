package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.auth.AuthLoginResponse
import com.teamsparta.backoffice.domain.user.dto.auth.AuthRequest
import com.teamsparta.backoffice.domain.user.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
        private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(
            @AuthenticationPrincipal oAuth2User: OAuth2User,
            @RequestBody request: AuthRequest
    ): ResponseEntity<AuthLoginResponse> {
        //이거 추가됨!!!
        return ResponseEntity.ok(authService.login(oAuth2User,request))
    }
}