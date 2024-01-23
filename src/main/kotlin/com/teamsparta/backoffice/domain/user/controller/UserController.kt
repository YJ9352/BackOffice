package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
        val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest : SignUpRequest) : ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequest))
    }

    @GetMapping("/{id}")
    fun searchMyInfo(@PathVariable id: Long) : ResponseEntity<SearchUserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchMyInfo(id))

    }
    @PatchMapping("/{id}")
    fun modifyMyInfo(@PathVariable id: Long, @RequestBody modifyUserRequest: ModifyUserRequest) : ResponseEntity<SearchUserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyMyInfo(id,modifyUserRequest))

    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest))
    }
}