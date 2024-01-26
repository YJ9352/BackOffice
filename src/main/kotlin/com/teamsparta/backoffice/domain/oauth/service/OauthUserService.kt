package com.teamsparta.backoffice.domain.oauth.service

import com.teamsparta.backoffice.domain.oauth.dto.GetOauthResponse
import com.teamsparta.backoffice.domain.oauth.dto.ModifyOauthRequest
import com.teamsparta.backoffice.domain.oauth.dto.OauthLoginResponse
import org.springframework.security.oauth2.core.user.OAuth2User

interface OauthUserService {
    fun login(oAuth2User: OAuth2User): OauthLoginResponse
    fun getMyInfo(email: String): GetOauthResponse
    fun modifyMyInfo(email: String, request: ModifyOauthRequest): GetOauthResponse

}
