package com.moneymate.moneymate.data.dto.mypage.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeleteAccountRequest(
    @SerialName("refreshToken")
    val refreshToken: String
)
