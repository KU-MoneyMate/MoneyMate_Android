package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.auth.request.IdPhoneCheckRequest
import com.moneymate.moneymate.data.dto.auth.request.LoginRequest
import com.moneymate.moneymate.data.dto.auth.request.PhoneVerificationCodeRequest
import com.moneymate.moneymate.data.dto.auth.request.RegisterRequest
import com.moneymate.moneymate.data.dto.auth.request.TokenReissueRequest
import com.moneymate.moneymate.data.dto.auth.response.IdPhoneCheckResponse
import com.moneymate.moneymate.data.dto.auth.response.LoginResponse
import com.moneymate.moneymate.data.dto.auth.response.PhoneVerificationCodeResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {
    // 회원가입
    @POST("register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    )

    // id 중복 확인
    @GET("user/check-id/{id}")
    suspend fun checkUserId(
        @Path("id") userId: String
    ) // TODO: Response 타입 정의 필요

    // id-전화번호 일치 검증
    @GET("user/check-id-ph")
    suspend fun checkIdPhone(
        @Body request: IdPhoneCheckRequest
    ): IdPhoneCheckResponse

    // 전화번호 인증 요청
    @POST("user/verify/phone-number-request")
    suspend fun requestPhoneVerification(
        @Body request: PhoneVerificationCodeRequest
    ): PhoneVerificationCodeResponse

//    // 전화번호 인증
//    @POST("user/verify/phone-number")
//    suspend fun verifyPhoneNumber(
//        @Body request: PhoneVerificationRequest
//    ): PhoneVerificationResponse

    // 로그인
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @POST("user/reissue-token")
    suspend fun reissueAccessToken(
        @Body request: TokenReissueRequest
    ): LoginResponse

}