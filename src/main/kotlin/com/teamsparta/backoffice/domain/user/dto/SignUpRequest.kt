package com.teamsparta.backoffice.domain.user.dto

data class SignUpRequest (
        val email : String,
        val password : String,
        val reenter : String,
        val name : String,
        val role : String,
        val phoneNumber : String
        )

