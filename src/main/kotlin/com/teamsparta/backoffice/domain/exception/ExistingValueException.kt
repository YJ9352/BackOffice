package com.teamsparta.backoffice.domain.exception

data class ExistingValueException(val format: String, val value: String) : RuntimeException(
        "${value}는 이미 존재하는 ${format}입니다."
)