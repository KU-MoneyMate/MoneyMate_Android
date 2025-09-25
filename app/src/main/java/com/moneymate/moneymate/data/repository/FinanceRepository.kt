package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.finance.NewsInfo
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.SavingProductItemDto
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

    suspend fun getDepositProducts(
        savingAmount: Int,
        period: Int,
        finGrpCode: String,
        regionCsv: String,
        intrType: String,
        joinDeny: String,
        joinWayCsv: String
    ): List<DepositProductItemDto> {
        return financeService.getDepositProducts(
            savingAmount = savingAmount,
            period = period,
            finGrpCode = finGrpCode,
            region = regionCsv,
            intrType = intrType,
            joinDeny = joinDeny,
            joinWay = joinWayCsv
        ).data
    }

    suspend fun getSavingProducts(
        savingAmount: Int,
        period: Int,
        finGrpCode: String,
        region: String,   // "all" or "seoul,busan,…"
        rsrvType: String, // all/S/F
        intrType: String, // all/S/M
        joinDeny: String, // 1/2/3
        joinWay: String   // "all" or "branch,internet,…"
    ): List<SavingProductItemDto> {
        return financeService.getSavingProducts(
            savingAmount, period, finGrpCode, region, rsrvType, intrType, joinDeny, joinWay
        ).data
    }

}