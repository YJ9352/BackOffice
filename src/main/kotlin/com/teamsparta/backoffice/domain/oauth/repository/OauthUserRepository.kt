package com.teamsparta.backoffice.domain.oauth.repository

import com.teamsparta.backoffice.domain.oauth.model.OauthUser
import org.springframework.data.jpa.repository.JpaRepository

interface OauthUserRepository : JpaRepository<OauthUser, Long> {
    fun findByEmail(email: String): OauthUser?
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickName: String): Boolean
    fun existsByPhoneNumber(phoneNumber: String): Boolean

}