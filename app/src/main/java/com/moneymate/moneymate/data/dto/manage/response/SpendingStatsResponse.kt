package com.moneymate.moneymate.data.dto.manage.response

import kotlinx.serialization.Serializable

@Serializable
data class SpendingStatsResponse(
    val status: String,
    val message: String,
    val data: SpendingStatsData
)

@Serializable
data class SpendingStatsData(
    val startDate: String,
    val endDate: String,
    val categoryTotals: Map<String, Long>
)

