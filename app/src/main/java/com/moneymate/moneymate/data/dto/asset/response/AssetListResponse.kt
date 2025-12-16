package com.moneymate.moneymate.data.dto.asset.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetListResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: AssetList
)

@Serializable
data class AssetList(
    @SerialName("asset")
    val asset: List<AssetInfo>
)

@Serializable
data class AssetInfo(
    @SerialName("assetUid")
    val uid: String,
    @SerialName("assetName")
    val name: String,
    @SerialName("assetPrice")
    val price: Long
)

