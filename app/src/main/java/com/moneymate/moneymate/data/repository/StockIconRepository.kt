package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.service.StockIconService

class StockIconRepository(
    private val stockIconService: StockIconService
) {
    // 주식 아이콘 조회
    suspend fun getStockIcon() = runCatching { stockIconService.getStockIcon() }
}