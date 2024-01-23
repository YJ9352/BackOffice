package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.LoginRequest
import com.teamsparta.backoffice.domain.user.dto.LoginResponse
import com.teamsparta.backoffice.domain.user.dto.SignUpRequest
import com.teamsparta.backoffice.domain.user.dto.UserResponse

interface UserService {
    fun signUp(request: SignUpRequest) : UserResponse

    fun login (request : LoginRequest) : LoginResponse
    fun searchMyInfo(id : Long) : UserResponse
    fun modifyMyInfo(id : Long) : UserResponse

}
