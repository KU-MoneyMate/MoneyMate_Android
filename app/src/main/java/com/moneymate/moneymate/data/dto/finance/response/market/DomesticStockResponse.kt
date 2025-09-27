package com.moneymate.moneymate.data.dto.finance.response.market

import kotlinx.serialization.Serializable

@Serializable
data class DomesticStockResponse(
    val stockListSortType: String,
    val stockListCategoryType: String,
    val stocks: List<DomesticStock>,
    val totalCount: Int,
    val page: Int,
    val pageSize: Int,
    val localOpenTimeDesc: String? = null,
    val marketStatus: String
)

@Serializable
data class DomesticStock(
    val stockType: String,
    val stockEndType: String,
    val itemCode: String, // 주식 코드
    val reutersCode: String, // 주식 코드(로이터)
    val stockName: String, // 주식 이름(한글)
    val sosok: String,
    val closePrice: String, // 종가
    val compareToPreviousClosePrice: String, // 전일종가 대비 차이 ex: "-6,000"
    val compareToPreviousPrice: CompareToPreviousPrice,
    val fluctuationsRatio: String, // 등락율 ex: "-1.75"
    val accumulatedTradingVolume: String,
    val accumulatedTradingValue: String,
    val accumulatedTradingValueKrwHangeul: String,
    val localTradedAt: String,
    val marketValue: String,
    val marketValueHangeul: String,
    val nav: String? = null,
    val threeMonthEarningRate: String? = null,
    val marketStatus: String,
    val tradeStopType: TradeStopType,
    val stockExchangeType: StockExchangeType,
    val endUrl: String,
    val overMarketPriceInfo: OverMarketPriceInfo? = null,
    val stockMiniImageChart: String
)

@Serializable
data class CompareToPreviousPrice(
    val code: String,
    val text: String, // 상승, 하락, 보합, 상한
    val name: String // RISING, FALLING, UNCHANGED, UPPER_LIMIT
)

@Serializable
data class TradeStopType(
    val code: String,
    val text: String,
    val name: String
)

@Serializable
data class StockExchangeType(
    val code: String,
    val zoneId: String,
    val nationType: String,
    val delayTime: Int,
    val startTime: String,
    val endTime: String,
    val closePriceSendTime: String,
    val nameKor: String,
    val nameEng: String,
    val stockType: String,
    val nationCode: String,
    val nationName: String,
    val name: String
)

@Serializable
data class OverMarketPriceInfo(
    val tradingSessionType: String,
    val overMarketStatus: String,
    val overPrice: String,
    val compareToPreviousPrice: CompareToPreviousPrice,
    val compareToPreviousClosePrice: String,
    val fluctuationsRatio: String,
    val localTradedAt: String,
    val tradeStopType: TradeStopType
)
