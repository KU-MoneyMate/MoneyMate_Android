package com.moneymate.moneymate.data.dto.auth.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneVerificationRequest(
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("verifyCode")
    val verifyCode: Int
)