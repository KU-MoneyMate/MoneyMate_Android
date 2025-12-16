package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.finance.response.market.DomesticStockResponse
import com.moneymate.moneymate.data.dto.finance.response.market.ExchangeRateResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface DomesticStockService {
    // 환율
    @GET("front-api/marketIndex/exchange/new")
    suspend fun getExchangeRate() : ExchangeRateResponse

    // 시가총액
    @GET("api/stocks/marketValue/all")
    suspend fun getDomesticMarketCap(
        @Query("page")  page: Int = 1,
        @Query("pageSize")  pageSize: Int = 20,
    ): DomesticStockResponse
    // 상승
    @GET("api/stocks/up/all")
    suspend fun getDomesticMarketRising(
        @Query("page")  page: Int = 1,
        @Query("pageSize")  pageSize: Int = 20,
    ): DomesticStockResponse

    // 하락
    @GET("api/stocks/down/all")
    suspend fun getDomesticMarketFalling(
        @Query("page")  page: Int = 1,
        @Query("pageSize")  pageSize: Int = 20,
    ): DomesticStockResponse
}