package com.teamsparta.backoffice.infra.security

data class ErrorResponse(
        // 전역 에러처리 구현 시에 exception 패키지로 빠질 예정입니다.
        val message: String?
)
