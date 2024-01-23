package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.LoginRequest
import com.teamsparta.backoffice.domain.user.dto.LoginResponse
import com.teamsparta.backoffice.domain.user.dto.SignUpRequest
import com.teamsparta.backoffice.domain.user.dto.UserResponse

class UserServiceImpl : UserService {
    override fun singUp(request: SignUpRequest): UserResponse {
        TODO("Not yet implemented")
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