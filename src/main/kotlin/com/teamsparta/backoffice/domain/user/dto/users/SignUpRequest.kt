package com.teamsparta.backoffice.domain.user.dto.users

data class SignUpRequest(
        val email: String,
        val password: String,
        val nickname: String,
        val role: String,
        val phoneNumber: String
)