package com.moneymate.moneymate.data.dto.manage.response

import kotlinx.serialization.Serializable

@Serializable
data class TotalAssetResponse(
    val status: String,
    val message: String,
    val data: Long
)