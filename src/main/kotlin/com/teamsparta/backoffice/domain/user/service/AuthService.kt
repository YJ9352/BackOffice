package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.auth.AuthRequest
import com.teamsparta.backoffice.domain.user.dto.auth.AuthLoginResponse
import org.springframework.security.oauth2.core.user.OAuth2User

interface AuthService {
    fun login(oAuth2User: OAuth2User, request: AuthRequest) : AuthLoginResponse
}