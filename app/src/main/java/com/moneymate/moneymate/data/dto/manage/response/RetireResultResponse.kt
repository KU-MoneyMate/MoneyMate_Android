package com.moneymate.moneymate.data.dto.manage.response

import kotlinx.serialization.Serializable

@Serializable
data class Asset(
    val age: Int,
    val asset: Long,
    val income: Long,
    val expense: Long
)