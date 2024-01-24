package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.model.*
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import com.teamsparta.backoffice.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service

class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtPlugin: JwtPlugin
) : UserService {
    //1. 회원가입
    override fun signUp(request: SignUpRequest): UserResponse {
        val user = User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                nickname = request.nickname,
                role = when (request.role) {
                    "ADMIN" -> UserRole.ADMIN
                    "CEO" -> UserRole.CEO
                    "CUSTOMER" -> UserRole.CUSTOMER
                    else -> throw IllegalArgumentException("Invalid role")
                },
                phoneNumber = request.phoneNumber,
                account = Account(0)
        )
        return userRepository.save(user).toResponseMail()
    }
    //2. 로그인
    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw IllegalArgumentException("Invalid role")
        return LoginResponse(
                accessToken = jwtPlugin.generateAccessToken(
                        subject = user.id.toString(),
                        email = user.email,
                        role = user.role.name

                )
        )
    }
    //3. 내 정보 조회
    override fun getMyInfo(id: Long): GetUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Invalid role")
        return user.toResponse()
    }
    //4. 내 정보 수정
    override fun modifyMyInfo(id: Long, request: ModifyUserRequest): GetUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("Ruler1")
        // 비밀번호 재입력
        //1. 입력받은 비밀번호와 유저의 비밀번호가 같은지 확인하고
        if (passwordEncoder.matches(request.password, user.password)) {
            //2. 입력받은 비밀번호와 재입력받은 비밀번호가 같으면
            if (request.password == request.reenter) {
                //3. 유저 정보를 수정하여 저장
                user.modifyUser(request)
                return user.toResponse()
            } else throw IllegalArgumentException("Ruler3")

        } else throw IllegalArgumentException("Ruler2")

    }
}