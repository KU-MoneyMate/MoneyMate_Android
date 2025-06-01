package com.moneymate.moneymate.data.dto.account.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetListResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<AssetInfo>
)

@Serializable
data class AssetInfo(
    @SerialName("assetUid")
    val uid: Int,
    @SerialName("assetName")
    val name: String,
    @SerialName("assetType")
    val type: String,
    @SerialName("assetPrice")
    val price: Int
)

