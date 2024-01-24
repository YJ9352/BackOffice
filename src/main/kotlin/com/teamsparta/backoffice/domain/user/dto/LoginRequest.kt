package com.teamsparta.backoffice.domain.user.dto

data class LoginRequest(
        val email: String,
        val password: String
)
