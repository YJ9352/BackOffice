package com.teamsparta.backoffice.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.teamsparta.backoffice.domain.exception.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntrypoint : AuthenticationEntryPoint {
    override fun commence(
            request: HttpServletRequest,
            response: HttpServletResponse,
            authException: AuthenticationException
    ) {
        // 인증에 대한 예외처리
        response.status = HttpServletResponse.SC_UNAUTHORIZED  // status 401
        response.contentType = MediaType.APPLICATION_JSON_VALUE // type : JSON
        response.characterEncoding = "UTF-8"

        val objectMapper = ObjectMapper()
        val jsonString = objectMapper.writeValueAsString(ErrorResponse("JWT verification failed"))
        response.writer.write(jsonString)
    }
}