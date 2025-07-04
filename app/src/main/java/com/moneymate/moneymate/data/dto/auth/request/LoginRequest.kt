package com.moneymate.moneymate.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("userid")
    val userId: String,
    @SerialName("password")
    val password: String
)
