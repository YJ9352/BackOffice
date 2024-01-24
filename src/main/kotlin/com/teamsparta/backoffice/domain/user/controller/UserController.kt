package com.teamsparta.backoffice.domain.user.controller

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.service.UserService
import com.teamsparta.backoffice.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/users")
@RestController
class UserController(
        val userService: UserService

) {
    // 1. 회원가입
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signUp(signUpRequest))
    }
    //2. 로그인
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest))
    }
    // 3. 내 정보 보기
    @GetMapping("/{userId}")
    // 입력한 userId와 현재 로그인한 사람의 id가 같아야 정보 조회 가능
    @PreAuthorize("#userPrincipal.id == #userId")
    fun getMyInfo(
            @PathVariable userId: Long,
            @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<GetUserResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMyInfo(userId))

    }
    //4 .내 정보 수정하기
    @PatchMapping("/{userId}")
    // 입력한 userId와 현재 로그인한 사람의 id가 같아야 정보 수정 가능
    @PreAuthorize("#userPrincipal.id == #userId")
    fun modifyMyInfo(@PathVariable userId: Long,
                     @RequestBody modifyUserRequest: ModifyUserRequest,
                     @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<GetUserResponse> {

        return ResponseEntity.status(HttpStatus.OK).body(userService.modifyMyInfo(userId, modifyUserRequest))

    }


}