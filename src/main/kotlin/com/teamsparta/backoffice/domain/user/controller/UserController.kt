package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.service.UserService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
@RestController
class UserController(
        val userService: UserService

) {
    // 1. 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequest))
    }

    //2. 로그인
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest))
    }

    // 3. 내 정보 보기
    @GetMapping
    // 입력한 userId와 현재 로그인한 사람의 id가 같아야 정보 조회 가능
    fun getMyInfo(
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<GetUserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMyInfo(userPrincipal.id))

    }

    //4 .내 정보 수정하기
    @PatchMapping
    // 입력한 userId와 현재 로그인한 사람의 id가 같아야 정보 수정 가능
    fun modifyMyInfo(
            @RequestBody modifyUserRequest: ModifyUserRequest,
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<GetUserResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyMyInfo(userPrincipal.id, modifyUserRequest))

    }


}