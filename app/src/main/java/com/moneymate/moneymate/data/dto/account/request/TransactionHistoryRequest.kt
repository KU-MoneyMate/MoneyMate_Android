package com.moneymate.moneymate.data.dto.account.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransactionHistoryRequest(
    @SerialName("accountUid")
    val accountUid: String,
    @SerialName("startDate")
    val startDate: String,
    @SerialName("endDate")
    val endDate: String
)
