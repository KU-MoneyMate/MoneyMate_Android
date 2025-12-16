package com.moneymate.moneymate.data.dto.asset.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetRegisterResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
)
