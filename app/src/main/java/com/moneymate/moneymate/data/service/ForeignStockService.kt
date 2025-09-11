package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.finance.response.market.ForeignStockResponse
import com.moneymate.moneymate.data.dto.finance.response.market.MarketIndexResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ForeignStockService {
    // 지수
    @GET("index/major")
    suspend fun getMarketIndexes(): List<MarketIndexResponse>

    // 시가총액
    @GET("stock/exchange/{exchange}/marketValue")
    suspend fun getForeignMarketCap(
        @Path("exchange") exchange: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): ForeignStockResponse

    // 상승
    @GET("stock/exchange/{exchange}/up")
    suspend fun getForeignMarketRising(
        @Path("exchange") exchange: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): ForeignStockResponse

    // 하락
    @GET("stock/exchange/{exchange}/down")
    suspend fun getForeignMarketFalling(
        @Path("exchange") exchange: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): ForeignStockResponse
}