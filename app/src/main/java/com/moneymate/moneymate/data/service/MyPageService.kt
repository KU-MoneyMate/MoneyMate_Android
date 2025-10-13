package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.mypage.request.UpdateUserRequest
import com.moneymate.moneymate.data.dto.mypage.request.VerifyPasswordRequest
import com.moneymate.moneymate.data.dto.mypage.response.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface MyPageService {
    @GET("/user/info")
    suspend fun getUserInfo(
    ): UserInfoResponse

    @PATCH("user/update")
    suspend fun updateUserInfo(
        @Body request: UpdateUserRequest
    )

    @POST("/user/verify/pw")
    suspend fun verifyPassword(
        @Body request: VerifyPasswordRequest
    ): Response<Void>
}