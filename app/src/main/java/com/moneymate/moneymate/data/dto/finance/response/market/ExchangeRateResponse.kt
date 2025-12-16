package com.moneymate.moneymate.data.dto.finance.response.market

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(
    val isSuccess: Boolean,
    val detailCode: String,
    val message: String,
    val result: List<ExchangeRateItem>
)

@Serializable
data class ExchangeRateItem(
    val name: String, // 통화명 ex: "미국 USD"
    val closePrice: String, // 종가
    val fluctuations: String, // 전일대비 차이 (음수면 - 로 시작) ex: "-0.08"
    val fluctuationsType: FluctuationsType,
    val fluctuationsRatio: String, // 등락률 (음수면 - 로 시작) ex: "-0.11"
    val localTradedAt: String,
    val marketStatus: String,
    val delayTimeName: String? = null,
    val reutersCode: String,
    val month: String? = null,
    val priceDataType: String,
    val delayTime: Int? = null,
    val unit: String? = null,
    val nationType: String? = null,
    val categoryType: String,
    val symbolCode: String,
    val exchangeCode: String? = null
)

@Serializable
data class FluctuationsType(
    val code: String,
    val text: String, // 상승, 하락, 보합, 상한
    val name: String // RISING, FALLING, UNCHANGED, UPPER_LIMIT
)
