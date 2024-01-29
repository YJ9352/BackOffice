package com.teamsparta.backoffice.domain.user.dto.users

data class ModifyUserRequest(
        val nickname: String,
        val password: String,
        var reenter: String,
        val phoneNumber: String,
)
