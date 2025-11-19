package com.moneymate.moneymate.data.dto.insight.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PortfolioInsightResponse(
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String,
    @SerialName("data")
    val data: PortfolioInsightInfo,
)

@Serializable
data class PortfolioInsightInfo(
    @SerialName("userCount")
    val userCount: Int,
    @SerialName("context")
    val context: String,
    @SerialName("currentTime")
    val currentTime: String,
)