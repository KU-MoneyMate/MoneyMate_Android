package com.moneymate.moneymate.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind

@Serializable
data class PhoneVerificationCodeRequest(
    @SerialName("phoneNumber")
    val phoneNumber: String
)
