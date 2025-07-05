package com.moneymate.moneymate.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("token") // TODO: 변경 예정
    val token: String,
)
