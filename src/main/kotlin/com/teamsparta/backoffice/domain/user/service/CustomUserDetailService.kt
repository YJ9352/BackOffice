package com.teamsparta.backoffice.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
@Service
class CustomUserDetailService : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        return super.loadUser(userRequest)
    }
}