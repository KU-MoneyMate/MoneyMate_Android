package com.moneymate.moneymate.data.dto.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdPhoneCheckRequest(
    @SerialName("id")
    val id : String,
    @SerialName("phone_number")
    val phoneNumber: String
)
