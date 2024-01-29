package com.teamsparta.backoffice.domain.oauth.service

import com.teamsparta.backoffice.domain.exception.StringNotFoundException
import com.teamsparta.backoffice.domain.oauth.dto.GetOauthResponse
import com.teamsparta.backoffice.domain.oauth.dto.ModifyOauthRequest
import com.teamsparta.backoffice.domain.oauth.dto.OauthLoginResponse
import com.teamsparta.backoffice.domain.oauth.model.OauthAccount
import com.teamsparta.backoffice.domain.oauth.model.OauthUser
import com.teamsparta.backoffice.domain.oauth.model.toResponse
import com.teamsparta.backoffice.domain.oauth.repository.OauthUserRepository
import com.teamsparta.backoffice.domain.user.model.UserRole
import com.teamsparta.backoffice.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service

class OauthUserServiceImpl(
        private val oauthUserRepository: OauthUserRepository,
        private val jwtPlugin: JwtPlugin
) : OauthUserService {
    @Transactional
    //2. 로그인
    override fun login(oAuth2User: OAuth2User): OauthLoginResponse {
        if (!oauthUserRepository.existsByEmail(oAuth2User.attributes["email"] as String)) {
            val oauthUser = OauthUser(
                    email = oAuth2User.attributes["email"] as String,
                    nickname = "임시닉네임123",
                    phoneNumber = null,
                    role = UserRole.CUSTOMER,
                    account = OauthAccount(0)
            )
            oauthUserRepository.save(oauthUser)
        }

        return OauthLoginResponse(
                accessToken = jwtPlugin.generateAuthToken(
                        oAuth2User = oAuth2User
                )
        )
    }

    override fun getMyInfo(email: String): GetOauthResponse {
        val oauthUser = oauthUserRepository.findByEmail(email) ?: throw StringNotFoundException("이메일", email)
        return oauthUser.toResponse()
    }

    override fun modifyMyInfo(email: String, request: ModifyOauthRequest): GetOauthResponse {
        val oauthUser = oauthUserRepository.findByEmail(email) ?: throw StringNotFoundException("이메일", email)
        oauthUser.modifyUser(request)
        return oauthUser.toResponse()
    }
}