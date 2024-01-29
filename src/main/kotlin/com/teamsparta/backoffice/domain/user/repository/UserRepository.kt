package com.teamsparta.backoffice.domain.user.repository

import com.teamsparta.backoffice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickName: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean

}