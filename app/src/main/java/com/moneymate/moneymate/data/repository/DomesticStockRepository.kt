package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.service.DomesticStockService

class DomesticStockRepository(
    private val domesticStockService: DomesticStockService
) {
    suspend fun getExchangeRate() =
        runCatching {
            domesticStockService.getExchangeRate()
        }

    suspend fun getDomesticMarketCap(page: Int, pageSize: Int) =
        runCatching {
            domesticStockService.getDomesticMarketCap(page, pageSize)
        }

    suspend fun getDomesticMarketRising(page: Int, pageSize: Int) =
        runCatching {
            domesticStockService.getDomesticMarketRising(page, pageSize)
        }

    suspend fun getDomesticMarketFalling(page: Int, pageSize: Int) =
        runCatching {
            domesticStockService.getDomesticMarketFalling(page, pageSize)
        }
}