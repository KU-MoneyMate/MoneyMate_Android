package com.moneymate.moneymate.data.dto.finance.response.market

import kotlinx.serialization.Serializable

@Serializable
data class MarketIndexResponse(
    val stockEndType: String,
    val stockExchangeType: IndexStockExchangeType,
    val reutersCode: String,
    val worldIndexSymbol: String? = null,
    val indexName: String, // 인덱스 이름(한글)
    val indexNameEng: String, // 인덱스 이름(영어)
    val localTradedAt: String,
    val compareToPreviousPrice: ChangeType,
    val closePrice: String,                    // 종가 ex: "3,480.00"
    val compareToPreviousClosePrice: String,   // 이전 종가와 비교  ex: "3.48"
    val fluctuationsRatio: String,             // 등락율 ex: "0.10"
    val accumulatedTradingVolume: Long,        // 큰 수여서 Long 권장
    val indexType: IndexType,
    val endUrl: String,
    val chartIqEndUrl: String? = null,
    val marketStatus: String,
    val delayTime: Int,
    val delayTimeName: String,
    val zoneId: String,
    val indexEndUrl: String,
    val exchangeOperatingTime: Boolean
)

@Serializable
data class IndexStockExchangeType(
    val code: String,
    val zoneId: String,
    val nationType: String,
    val delayTime: Int,
    val startTime: String,
    val endTime: String,
    val closePriceSendTime: String,
    val nameKor: String,
    val nameEng: String,
    val name: String,
    val nationName: String,
    val stockType: String,
    val nationCode: String
)

@Serializable
data class ChangeType(
    val code: String,
    val text: String, // 상승, 하락, 보합
    val name: String // RISING, FALLING, UNCHANGED
)

@Serializable
data class IndexType(
    val stockExchangeType: IndexStockExchangeType,
    val reutersCode: String,
    val worldSymbol: String,
    val name: String,
    val fullName: String,
    val description: String,
    val continentType: ContinentType,
    val symbolCode: String,
    val currencyType: CurrencyType? = null,
    val korIndexType: Boolean,
    val indexTypeByDollar: Boolean,
    val indexTypeByTransport: Boolean,
    val transportIndexType: Boolean
)

@Serializable
data class ContinentType(
    val name: String,
    val nameEng: String,
    val code: String
)

@Serializable
data class CurrencyType(
    val code: String,
    val text: String,
    val name: String
)
