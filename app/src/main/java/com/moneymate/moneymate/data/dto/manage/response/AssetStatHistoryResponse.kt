package com.moneymate.moneymate.data.dto.manage.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetStatHistoryResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<AssetStatHistoryData>
)

@Serializable
data class AssetStatHistoryData(
    @SerialName("date")
    val date: String,
    @SerialName("totalPrice")
    val totalPrice: String,
)
