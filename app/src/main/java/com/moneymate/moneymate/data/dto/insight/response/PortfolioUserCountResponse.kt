package com.moneymate.moneymate.data.dto.insight.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PortfolioUserCountResponse(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String,
    @SerialName("data")
    val data: UserCountInfo,
)

@Serializable
data class UserCountInfo(
    @SerialName("userCount")
    val userCount: Int,
    @SerialName("context")
    val context: String,
    @SerialName("currentTime")
    val currentTime: String,
)
