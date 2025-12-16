package com.moneymate.moneymate.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("userId")
    val userId: String,
    @SerialName("password")
    val password: String,
    @SerialName("userName")
    val userName: String,
    @SerialName("birthday")
    val birthday: String,
    @SerialName("phoneNumber")
    val phoneNumber: String
)
