package com.moneymate.moneymate.data.dto.asset.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StockListResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: StockList
)

@Serializable
data class StockList(
    @SerialName("stockList")
    val asset: List<StockInfo>
)

@Serializable
data class StockInfo(
    @SerialName("accountName")
    val accountName: String,
    @SerialName("stockName")
    val stockName: String,
    @SerialName("ticker")
    val ticker: String,
    @SerialName("quantity")
    val quantity: String,
    @SerialName("totalPrice")
    val totalPrice: String,
    @SerialName("profit")
    val profit: String,
)