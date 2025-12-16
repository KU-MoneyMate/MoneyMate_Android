package com.moneymate.moneymate.data.dto.auth.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: LogoutResult
)

@Serializable
data class LogoutResult(
    @SerialName("message")
    val message: String
)
