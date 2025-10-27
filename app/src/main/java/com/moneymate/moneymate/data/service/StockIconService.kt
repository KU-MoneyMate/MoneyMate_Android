package com.moneymate.moneymate.data.service

import retrofit2.http.GET

interface StockIconService {
    @GET("stock_icons")
    suspend fun getStockIcon()
}