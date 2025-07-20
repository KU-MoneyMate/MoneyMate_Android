package com.moneymate.moneymate.data.dto.finance.response

import com.moneymate.moneymate.data.dto.finance.NewsInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDetailResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<NewsInfo>,
)
