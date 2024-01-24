package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.model.*
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import com.teamsparta.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service

class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtPlugin: JwtPlugin
) : UserService {
    override fun signUp(request: SignUpRequest): UserResponse {
        val user = User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname,
                role = when (request.role) {
                    "ADMIN" -> UserRole.ADMIN
                    "CEO" -> UserRole.CEO
                    "CUSTOMER" -> UserRole.CUSTOMER
                    else -> throw IllegalArgumentException("Invalid role")
                },
                phoneNumber = request.phoneNumber,
                account = Account(0)
        )
        return userRepository.save(user).toResponseMail()
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw IllegalArgumentException("Invalid role")
        return LoginResponse(
                accessToken = jwtPlugin.generateAccessToken(
                        subject = user.id.toString(),
                        email = user.email,
                        role = user.role.name

                )
        )
    }

    override fun searchMyInfo(id: Long): SearchUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Invalid role")
        return user.toResponse()
    }

    override fun modifyMyInfo(id: Long, request: ModifyUserRequest): SearchUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Ruler1")
        if (passwordEncoder.matches(request.password, user.password)) {
            if (request.password == request.reenter) {
                user.modifyUser(request)
                return user.toResponse()
            } else throw IllegalArgumentException("Ruler3")

        } else throw IllegalArgumentException("Ruler2")

    }
}