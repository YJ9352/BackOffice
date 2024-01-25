package com.teamsparta.backoffice.domain.exception

import com.teamsparta.backoffice.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(ExistingValueException::class)
    fun handleFormatException(e: ExistingValueException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(CustomException::class)
    fun handlePasswordException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(StringNotFoundException::class)
    fun handleStringNotFoundException(e: StringNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(message = e.message))
    }
}