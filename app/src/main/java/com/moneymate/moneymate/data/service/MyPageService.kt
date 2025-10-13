package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.mypage.request.UpdateUserRequest
import com.moneymate.moneymate.data.dto.mypage.response.UserInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface MyPageService {
    @GET("/user/info")
    suspend fun getUserInfo(
    ): UserInfoResponse

    @PATCH("user/update")
    suspend fun updateUserInfo(
        @Body request: UpdateUserRequest
    )
}