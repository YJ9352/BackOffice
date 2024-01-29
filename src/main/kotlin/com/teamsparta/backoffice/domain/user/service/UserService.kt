package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.users.*

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse
    fun login(request: LoginRequest): LoginResponse
    fun getMyInfo(id: Long): GetUserResponse
    fun modifyMyInfo(id: Long, request: ModifyUserRequest): GetUserResponse

}
