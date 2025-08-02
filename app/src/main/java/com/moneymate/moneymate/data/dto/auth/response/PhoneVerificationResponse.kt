package com.moneymate.moneymate.data.dto.auth.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneVerificationResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: VerificationResult
)

@Serializable
data class VerificationResult(
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("verifyCode")
    val verifyCode: Int,
    @SerialName("message")
    val message: String,
    @SerialName("userVerifyCode")
    val idVerifyCode: String,
)
