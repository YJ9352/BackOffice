package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.service.UserService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    @PreAuthorize("#userPrincipal.id == #id")
    fun searchMyInfo(
            @PathVariable id: Long,
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ) : ResponseEntity<SearchUserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.searchMyInfo(id))

    }
    @PatchMapping("/{id}")
    @PreAuthorize("#userPrincipal.id == #id")
    fun modifyMyInfo(@PathVariable id: Long,
                     @RequestBody modifyUserRequest: ModifyUserRequest,
                     @AuthenticationPrincipal userPrincipal: UserPrincipal
    ) : ResponseEntity<SearchUserResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyMyInfo(id,modifyUserRequest))

    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest))
    }
}