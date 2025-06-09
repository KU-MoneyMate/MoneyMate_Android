package com.moneymate.moneymate.data.dto.mypage.response

import kotlinx.serialization.Serializable

@Serializable
data class RetireResultResponse(
    val assets: List<Asset>
)

@Serializable
data class Asset(
    val age: Int,
    val asset: Long,
    val income: Long,
    val expense: Long
)