package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.service.ForeignStockService

class ForeignStockRepository(
    private val foreignStockService: ForeignStockService
) {
    suspend fun getMarketIndexes() =
        runCatching {
            foreignStockService.getMarketIndexes()
        }

    suspend fun getForeignMarketCap(exchange: String, page: Int, pageSize: Int) =
        runCatching {
            foreignStockService.getForeignMarketCap(exchange, page, pageSize)
        }

    suspend fun getForeignMarketRising(exchange: String, page: Int, pageSize: Int) =
        runCatching {
            foreignStockService.getForeignMarketRising(exchange, page, pageSize)
        }

    suspend fun getForeignMarketFalling(exchange: String, page: Int, pageSize: Int) =
        runCatching {
            foreignStockService.getForeignMarketFalling(exchange, page, pageSize)
        }
}