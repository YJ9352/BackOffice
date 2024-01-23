package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.LoginRequest
import com.teamsparta.backoffice.domain.user.dto.LoginResponse
import com.teamsparta.backoffice.domain.user.dto.SignUpRequest
import com.teamsparta.backoffice.domain.user.dto.UserResponse
import com.teamsparta.backoffice.domain.user.model.User
import com.teamsparta.backoffice.domain.user.model.UserRole
import com.teamsparta.backoffice.domain.user.model.toResponse
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service

class UserServiceImpl(
        private val userRepository: UserRepository
) : UserService {
    override fun signUp(request: SignUpRequest): UserResponse {
        val user = User(
                email = request.email,
                password = request.password,
                nickname = request.nickname,
                role = when (request.role){
                    "ADMIN" -> UserRole.ADMIN
                    "CEO" -> UserRole.CEO
                    "CUSTOMER" -> UserRole.CUSTOMER
                    else-> throw IllegalArgumentException("Invalid role")
                },
                phoneNumber = request.phoneNumber,
                balance = 0
        )
        return userRepository.save(user).toResponse()
    }

    override fun login(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }

    override fun searchMyInfo(id: Long): UserResponse {
        TODO("Not yet implemented")
    }

    override fun modifyMyInfo(id: Long): UserResponse {
        TODO("Not yet implemented")
    }
}