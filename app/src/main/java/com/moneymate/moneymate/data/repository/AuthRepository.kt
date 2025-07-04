package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.auth.request.RegisterRequest
import com.moneymate.moneymate.data.service.AuthService

class AuthRepository(
    private val authService: AuthService
) {
    // 회원가입
    suspend fun registerUser(
        userId: String,
        userName: String,
        password: String,
        phoneNumber: String,
        birthday: String
    ) = runCatching {
        authService.registerUser(
            RegisterRequest(
                userId = userId,
                userName = userName,
                password = password,
                phoneNumber = phoneNumber,
                birthday = birthday
            )
        )
    }
}