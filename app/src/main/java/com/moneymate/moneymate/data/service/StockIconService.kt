package com.moneymate.moneymate.data.service

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface StockIconService {
    @GET("stock_icons/{ticker}.svg")
    suspend fun getStockIcon(
        @Path("ticker") ticker: String
    ): ResponseBody
}