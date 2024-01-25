package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.exception.ExistingValueException
import com.teamsparta.backoffice.domain.exception.StringNotFoundException
import com.teamsparta.backoffice.domain.user.dto.auth.AuthRequest
import com.teamsparta.backoffice.domain.user.dto.auth.AuthLoginResponse
import com.teamsparta.backoffice.domain.user.model.*
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import com.teamsparta.backoffice.infra.regex.RegexFunc
import com.teamsparta.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl (
        private val userRepository: UserRepository,
        private val jwtPlugin: JwtPlugin,
        private val passwordEncoder: PasswordEncoder,
        private val regexFunc: RegexFunc
)
    : AuthService {
    override fun login(oAuth2User: OAuth2User,request : AuthRequest): AuthLoginResponse {
        if(!userRepository.existsByEmail(oAuth2User.attributes["email"] as String)) {
            val checkNickname = userRepository.existsByNickname(request.nickname)
            val checkPhoneNumber = userRepository.existsByPhoneNumber(request.phoneNumber)
            val user = User(
                    email = oAuth2User.attributes["email"] as String,
                    password = passwordEncoder.encode(regexFunc.regexPassword(request.password)),
                    nickname = request.nickname,
                    role = UserRole.CUSTOMER ,
                    phoneNumber = regexFunc.regexPhoneNumber(request.phoneNumber),
                    account = Account(0)
            )
            when {
                (checkNickname) -> {
                    throw ExistingValueException("닉네임", request.nickname)
                }

                (checkPhoneNumber) -> {
                    throw ExistingValueException("전화번호", request.phoneNumber)
                }

                else -> userRepository.save(user)
            }
        }
    return AuthLoginResponse(
            accessToken = jwtPlugin.generateAuthAccessToken(
                    oAuth2User = oAuth2User
            )
    )
    }
}