package com.moneymate.moneymate.data.repository

import android.util.Log
import com.moneymate.moneymate.data.dto.auth.request.LoginRequest
import com.moneymate.moneymate.data.dto.auth.request.LogoutRequest
import com.moneymate.moneymate.data.dto.auth.request.PhoneVerificationCodeRequest
import com.moneymate.moneymate.data.dto.auth.request.PhoneVerificationRequest
import com.moneymate.moneymate.data.dto.auth.request.RegisterRequest
import com.moneymate.moneymate.data.dto.auth.response.CheckExistingIdResponse
import com.moneymate.moneymate.data.service.AuthService
import com.moneymate.moneymate.util.auth.TokenManager
import kotlinx.coroutines.flow.first
import retrofit2.HttpException

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
        tokenManager.saveAccessToken(response.data.accessToken)
        tokenManager.saveRefreshToken(response.data.refreshToken)
        Log.d("AuthRepository", "Access Token: ${tokenManager.getAccessToken().first()}")
        Log.d("AuthRepository", "Refresh Token: ${tokenManager.getRefreshToken().first()}")
    }

    // id 중복 확인
    suspend fun checkUserId(userId: String) = runCatching {
        try {
            val response = authService.checkUserId(userId)
            Log.d("AuthRepository", "ID 중복 확인 응답: ${response.status}, ${response.message}, ${response.data}")
            response
        } catch (e: HttpException) {
            when (e.code()) {
                409 -> {
                    // 409 응답을 정상적으로 처리
                    CheckExistingIdResponse(
                        status = "Conflict",
                        message = "이미 존재하는 ID 입니다",
                        data = "[409] userId 중복"
                    )
                }
                else -> throw e
            }
        }
    }.onFailure {
        Log.d("AuthRepository", "ID 중복 확인 네트워크 에러: ${it.message}")
        throw it
    }

    // 인증번호 요청
    suspend fun requestPhoneVerification(phoneNumber: String) = runCatching {
        val response = authService.requestPhoneVerification(
            PhoneVerificationCodeRequest(phoneNumber)
        )
        response
    }.onFailure {
        Log.d("AuthRepository", "인증번호 요청 실패: ${it.message}")
    }

    // 인증번호 검증
    suspend fun verifyPhoneNumber(
        phoneNumber: String,
        verifyCode: Int
    ) = runCatching {
        val response = authService.verifyPhoneNumber(
            PhoneVerificationRequest(
                phoneNumber = phoneNumber,
                verifyCode = verifyCode
            )
        )
        response
    }.onFailure {
        Log.d("AuthRepository", "전화번호 인증 실패: ${it.message}")
    }

    //로그아웃
    suspend fun logout(
        refreshToken: String
    ) = runCatching {
        authService.logout(
            request = LogoutRequest(refreshToken = refreshToken)
        )
        // API 요청 성공 시 로컬 토큰 삭제
        tokenManager.clearToken()
        Log.d("AuthRepository", "로그아웃 성공 및 로컬 토큰 삭제 완료")
    }.onFailure {
        // API 요청 실패 시 (예: 네트워크 오류, 5xx 에러)
        // 보안상 실패하더라도 로컬 토큰은 삭제하여 강제 로그아웃 상태로 만드는 것이 일반적입니다.
        tokenManager.clearToken()
        Log.e("AuthRepository", "로그아웃 API 요청 실패. 로컬 토큰은 삭제됩니다: ${it.message}")
        throw it // ViewModel에서 실패를 처리할 수 있도록 예외 던지기
    }
}