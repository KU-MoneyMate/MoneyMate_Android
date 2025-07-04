package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.auth.request.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("register")
    suspend fun registerUser(
        @Body request: RegisterRequest
    )
}