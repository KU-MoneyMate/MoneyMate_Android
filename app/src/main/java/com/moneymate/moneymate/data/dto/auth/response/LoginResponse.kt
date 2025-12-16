package com.moneymate.moneymate.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("status")
    val statue: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: TokenResponse,
)

@Serializable
data class TokenResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("grantType")
    val grantType: String,
)
