package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.*

interface UserService {
    fun signUp(request: SignUpRequest) : UserResponse

    fun login (request : LoginRequest) : LoginResponse
    fun searchMyInfo(id : Long) : SearchUserResponse
    fun modifyMyInfo(id : Long, request: ModifyUserRequest) : SearchUserResponse

}
