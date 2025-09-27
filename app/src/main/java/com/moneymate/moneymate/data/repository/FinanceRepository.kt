package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.finance.NewsInfo
import com.moneymate.moneymate.data.dto.finance.response.CreditLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.MortgageLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.RentHouseLoanProductItemDto
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

    suspend fun getMortgageLoanProducts(
        mrtgType: String,
        finGrpCode: String,
        region: String,
        rpayType: String,
        lendRateType: String,
        joinWay: String
    ): List<MortgageLoanProductItemDto> {
        return financeService.getMortgageLoanProducts(
            mrtgType, finGrpCode, region, rpayType, lendRateType, joinWay
        ).data
    }

    suspend fun getRentHouseLoanProducts(
        finGrpCode: String,
        region: String,
        rpayType: String,
        lendRateType: String,
        joinWay: String
    ): List<RentHouseLoanProductItemDto> {
        return financeService.getRentHouseLoanProducts(
            finGrpCode, region, rpayType, lendRateType, joinWay
        ).data
    }

    suspend fun getCreditLoanProducts(
        finGrpCode: String,
        region: String,
        crdtPrdtType: String,
        crdtLendRateType: String,
        joinWay: String
    ): List<CreditLoanProductItemDto> {
        return financeService.getCreditLoanProducts(
            finGrpCode, region, crdtPrdtType, crdtLendRateType, joinWay
        ).data
    }
}