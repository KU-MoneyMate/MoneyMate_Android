package com.moneymate.moneymate.data.dto.insight.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsInsightResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<NewsSummaryData>
)

@Serializable
data class NewsSummaryData(
    @SerialName("category")
    val category: String,
    @SerialName("content")
    val content: String,
    @SerialName("generatedTime")
    val generatedTime: String,
)
