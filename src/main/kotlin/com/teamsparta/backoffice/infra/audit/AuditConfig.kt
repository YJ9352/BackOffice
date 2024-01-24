package com.teamsparta.backoffice.infra.audit

import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
class AuditConfig {
    @Bean
    fun auditorAware() = AuditorAware {
        val userPrincipal: UserPrincipal
        Optional.ofNullable(SecurityContextHolder.getContext())
                .map { it.authentication }
                .filter { it.isAuthenticated && !it.name.equals("anonymousUser") }
                .map { it.principal as UserPrincipal }
                .map { it.email }
    }
}