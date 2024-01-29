package com.teamsparta.backoffice.domain.user.dto.users

data class GetUserResponse(
        val email: String,
        val nickname: String,
        val role: String,
        val phoneNumber: String,
)
