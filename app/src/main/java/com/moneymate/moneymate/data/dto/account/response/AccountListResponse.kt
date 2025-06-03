package com.moneymate.moneymate.data.dto.account.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountListResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: AccountList
)

@Serializable
data class AccountList(
    @SerialName("account")
    val account: List<AccountInfo>
)

@Serializable
data class AccountInfo(
    @SerialName("accountUid")
    val uid : Int,
    @SerialName("accountBank")
    val bankCode: String,
    @SerialName("accountName")
    val name: String,
    @SerialName("accountType")
    val type: String,
    @SerialName("accountNumber")
    val number: String,
    @SerialName("accountBalance")
    val balance: Int
)
