package com.teamsparta.backoffice.domain.user.service

import com.teamsparta.backoffice.domain.exception.CustomException
import com.teamsparta.backoffice.domain.exception.ExistingValueException
import com.teamsparta.backoffice.domain.exception.ModelNotFoundException
import com.teamsparta.backoffice.domain.exception.StringNotFoundException
import com.teamsparta.backoffice.domain.user.dto.*
import com.teamsparta.backoffice.domain.user.model.*
import com.teamsparta.backoffice.domain.user.repository.UserRepository
import com.teamsparta.backoffice.infra.regex.RegexFunc
import com.teamsparta.backoffice.infra.security.jwt.JwtPlugin
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service

class UserServiceImpl(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtPlugin: JwtPlugin,
        private val regexFunc: RegexFunc
) : UserService {
    //1. 회원가입
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        val checkMail = userRepository.existsByEmail(request.email)
        val checkNickname = userRepository.existsByNickname(request.nickname)
        val checkPhoneNumber = userRepository.existsByPhoneNumber(request.phoneNumber)
        val user = User(
                email = regexFunc.regexEmail(request.email),
                password = passwordEncoder.encode(regexFunc.regexPassword(request.password)),
                nickname = request.nickname,
                role = when (request.role) {
                    "ADMIN" -> UserRole.ADMIN
                    "CEO" -> UserRole.CEO
                    "CUSTOMER" -> UserRole.CUSTOMER
                    else -> throw StringNotFoundException("존재하지 않는 역할", request.role)
                },
                phoneNumber = regexFunc.regexPhoneNumber(request.phoneNumber),
                account = Account(0)
        )
        return when {
            (checkMail) -> {
                throw ExistingValueException("메일", request.email)
            }

            (checkNickname) -> {
                throw ExistingValueException("닉네임", request.nickname)
            }

            (checkPhoneNumber) -> {
                throw ExistingValueException("전화번호", request.phoneNumber)
            }

            else -> userRepository.save(user).toResponseMail()
        }

    }

    @Transactional
    //2. 로그인
    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email)
                ?: throw StringNotFoundException("존재하지 않는 이메일", request.email)
        if (!passwordEncoder.matches(request.password, user.password)) {
            throw CustomException("비밀번호가 일치하지 않습니다.")
        }
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
        val user = userRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("MyInfo", id)
        return user.toResponse()
    }

    //4. 내 정보 수정
    @Transactional
    override fun modifyMyInfo(id: Long, request: ModifyUserRequest): GetUserResponse {
        val user = userRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("MyInfo", id)
        // 비밀번호 재입력
        //1. 입력받은 비밀번호와 유저의 비밀번호가 같은지 확인하고
        if (passwordEncoder.matches(request.password, user.password)) {
            //2. 입력받은 비밀번호와 재입력받은 비밀번호가 같으면
            if (request.password == request.reenter) {
                //3. 유저 정보를 수정하여 저장
                user.modifyUser(request)
                return user.toResponse()
            } else throw CustomException("재입력된 비밀번호가 일치하지 않습니다.")

        } else throw CustomException("잘못된 비밀번호가 입력되었습니다.")

    }
}