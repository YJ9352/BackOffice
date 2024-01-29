package com.teamsparta.backoffice.domain.oauth.repository

import com.teamsparta.backoffice.domain.oauth.model.OauthAccount
import org.springframework.data.jpa.repository.JpaRepository

interface OauthAccountRepository : JpaRepository<OauthAccount, Long>