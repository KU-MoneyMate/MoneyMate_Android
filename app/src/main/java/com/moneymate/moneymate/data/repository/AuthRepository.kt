package com.moneymate.moneymate.data.repository

import android.util.Log
import com.moneymate.moneymate.data.dto.auth.request.LoginRequest
import com.moneymate.moneymate.data.dto.auth.request.RegisterRequest
import com.moneymate.moneymate.data.service.AuthService
import com.moneymate.moneymate.util.auth.TokenManager
import kotlinx.coroutines.flow.first

class AuthRepository(
    private val authService: AuthService,
    private val tokenManager: TokenManager
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

    // 로그인
    suspend fun login(
        userId: String,
        password: String
    ) = runCatching {
        val response = authService.login(
            LoginRequest(
                userId = userId,
                password = password
            )
        )
        // 로그인 성공 시 토큰 저장
        tokenManager.saveAccessToken(response.accessToken)
        tokenManager.saveRefreshToken(response.refreshToken)
        Log.d("AuthRepository", "Access Token: ${tokenManager.getAccessToken().first()}")
        Log.d("AuthRepository", "Refresh Token: ${tokenManager.getRefreshToken().first()}")
    }
}