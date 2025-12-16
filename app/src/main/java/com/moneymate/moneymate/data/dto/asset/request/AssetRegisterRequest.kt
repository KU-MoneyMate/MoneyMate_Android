package com.moneymate.moneymate.data.dto.asset.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssetRegisterRequest(
    @SerialName("assetName")
    val name: String,
    @SerialName("assetPrice")
    val price: Long
)
