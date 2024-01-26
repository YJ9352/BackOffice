package com.teamsparta.backoffice.domain.oauth.dto

data class GetOauthResponse(
        val email: String,
        val nickname: String?,
        val role: String,
        val phoneNumber: String?,
)
