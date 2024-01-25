package com.teamsparta.backoffice.infra.regex

import com.teamsparta.backoffice.domain.exception.CustomException
import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class RegexFunc {
    fun regexEmail(email: String): String {
        val regexEmail = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$"
        if (!Pattern.matches(regexEmail, email)) {
            throw CustomException("올바른 이메일 형식에 따라 입력해 주시기 바랍니다.")
        } else return email
    }

    fun regexPassword(password: String): String {
        val regexPassword = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*])[a-zA-Z0-9!@#%^&*]{8,15}\$"
        if (!Pattern.matches(regexPassword, password)) {
            throw CustomException("영문, 숫자, 특수문자를 포함한 8~15자리로 입력해주세요"
            )
        } else return password
    }

    fun regexPhoneNumber(phoneNumber: String): String {
        val regexPhoneNumber = "(\\d{3})(\\d{3,4})(\\d{4})"
        if (Pattern.matches(regexPhoneNumber, phoneNumber)) {
            Regex(regexPhoneNumber).replace(phoneNumber, "$1-$2-$3")
        } else throw CustomException("올바른 전화번호를 입력해 주세요.")
        return phoneNumber
    }
}