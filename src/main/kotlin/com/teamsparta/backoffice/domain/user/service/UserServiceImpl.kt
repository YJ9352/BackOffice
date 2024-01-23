package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.model.User
import com.teamsparta.backoffice.domain.user.model.UserRole
import com.teamsparta.backoffice.domain.user.model.toResponse
import com.teamsparta.backoffice.domain.user.model.toResponseMail
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
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
        return userRepository.save(user).toResponseMail()
    }

    override fun login(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }

    override fun searchMyInfo(id: Long): SearchUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Invalid role")
        return user.toResponse()
    }

    override fun modifyMyInfo(id: Long, request: ModifyUserRequest): SearchUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Invalid role")
        if (request.password == request.reenter) {
            user.modifyUser(request)
            return user.toResponse()
        }
        else throw IllegalArgumentException("Invalid role")

    }
}