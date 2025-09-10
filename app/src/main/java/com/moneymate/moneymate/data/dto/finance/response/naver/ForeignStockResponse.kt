package com.moneymate.moneymate.data.dto.finance.response.naver

import kotlinx.serialization.Serializable

@Serializable
data class ForeignStockResponse(
    val page: Int,
    val pageSize: Int,
    val totalCount: Int,
    val stocks: List<StockDto>,
    val marketStatus: String,
    val localOpenTimeDesc: String? = null,
    val localOpenTime: String? = null
)

@Serializable
data class StockDto(
    val stockType: String,
    val stockEndType: String,
    val compareToPreviousPrice: CompareToPreviousPriceDto,
    val nationType: String,
    val stockExchangeType: StockExchangeTypeDto,
    val reutersCode: String? = null,
    val symbolCode: String, // 주식 코드
    val stockName: String, // 주식 이름(한글)
    val stockNameEng: String, // 주식 이름(영어)
    val reutersIndustryCode: String? = null,
    val industryCodeType: IndustryCodeTypeDto,
    val openPrice: String? = null, // 시가
    val closePrice: String? = null, // 종가
    val compareToPreviousClosePrice: String? = null, // 전일대비 차이 (음수면 - 로 시작)
    val fluctuationsRatio: String? = null, // 등락률 (음수면 - 로 시작)
    val executedVolume: String? = null,
    val accumulatedTradingVolume: String? = null, // 누적 거래량
    val accumulatedTradingValue: String? = null, // 누적 거래대금
    val accumulatedTradingValueKrwHangeul: String? = null, // 누적 거래대금(원)
    val localTradedAt: String? = null,
    val marketStatus: String? = null,
    val overMarketPriceInfo: String? = null,
    val marketValue: String? = null,
    val marketValueHangeul: String? = null,
    val marketValueKrwHangeul: String? = null,
    val currencyType: CurrencyTypeDto? = null,
    val dividend: String? = null,
    val dividendPayAt: String? = null,
    val tradeStopType: TradeStopTypeDto? = null,
    val endUrl: String? = null,
    val delayTime: Int? = null,
    val delayTimeName: String? = null,
    val stockEndUrl: String? = null,
    val exchangeOperatingTime: Boolean? = null
)

@Serializable
data class CompareToPreviousPriceDto(
    val code: String,
    val text: String, // 상승, 하락, 보합
    val name: String // RISING, FALLING, UNCHANGED
)

@Serializable
data class StockExchangeTypeDto(
    val code: String,
    val zoneId: String,
    val nationType: String,
    val delayTime: Int,
    val startTime: String,
    val endTime: String,
    val closePriceSendTime: String,
    val nameKor: String,
    val nameEng: String,
    val nationCode: String,
    val nationName: String,
    val stockType: String,
    val name: String
)

@Serializable
data class IndustryCodeTypeDto(
    val code: String,
    val industryGroupKor: String, // 분야 정보(한글)
    val name: String
)

@Serializable
data class CurrencyTypeDto(
    val code: String,
    val text: String,
    val name: String
)

@Serializable
data class TradeStopTypeDto(
    val code: String,
    val text: String,
    val name: String
)
