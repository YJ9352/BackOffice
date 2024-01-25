package com.teamsparta.backoffice.domain.user.dto.auth

data class AuthRequest(
        val password: String,
        val nickname: String,
        val role: String,
        val phoneNumber: String
)
