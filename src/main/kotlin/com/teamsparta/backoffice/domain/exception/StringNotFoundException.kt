package com.teamsparta.backoffice.domain.exception

data class StringNotFoundException(val format: String, val value: String) : RuntimeException(
        "${value}는 존재하지 않는 ${format}입니다."
)
