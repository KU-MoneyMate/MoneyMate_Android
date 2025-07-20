package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.finance.response.NewsInfo
import com.moneymate.moneymate.data.service.FinanceService

class FinanceRepository(
    private val financeService: FinanceService
) {
    suspend fun getNewsList() : List<NewsInfo> {
        return financeService.getNewsList().data
    }
}