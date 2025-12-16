package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.auth.request.TokenReissueRequest
import com.moneymate.moneymate.data.dto.auth.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {
    @POST("user/reissue-token")
    suspend fun reissueAccessToken(
        @Body request: TokenReissueRequest
    ): LoginResponse
} 