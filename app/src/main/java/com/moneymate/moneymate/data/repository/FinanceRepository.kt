package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.finance.NewsInfo
import com.moneymate.moneymate.data.service.FinanceService

class FinanceRepository(
    private val financeService: FinanceService
) {
    suspend fun getNewsList() : List<NewsInfo> {
        return financeService.getNewsList().data
    }

    suspend fun getCategoryNews(publisher : String, category:String) : List<NewsInfo> {
        return financeService.getNewsDetail(publisher,category).data
    }
}