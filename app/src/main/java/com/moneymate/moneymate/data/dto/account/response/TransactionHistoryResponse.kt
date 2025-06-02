package com.moneymate.moneymate.data.dto.account.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionHistoryResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Transaction>
)

@Serializable
data class Transaction(
    @SerialName("trDate")
    val date: String,
    @SerialName("trTime")
    val time: String,
    @SerialName("trOut")
    val outAmount: Int,
    @SerialName("trIn")
    val inAmount: Int,
    @SerialName("trAfterBalance")
    val afterBalance: Int,
    @SerialName("trDest")
    val destination: String
)
