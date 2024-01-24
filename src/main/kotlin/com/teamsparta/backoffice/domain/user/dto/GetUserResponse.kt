package com.teamsparta.backoffice.domain.user.dto

data class GetUserResponse(
        val email: String,
        val nickname: String,
        val role: String,
        val phoneNumber: String,
)
