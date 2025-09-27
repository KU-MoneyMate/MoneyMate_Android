package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.finance.response.CreditLoanProductResponse
import com.moneymate.moneymate.data.dto.finance.response.DepositProductResponse
import com.moneymate.moneymate.data.dto.finance.response.MortgageLoanProductResponse
import com.moneymate.moneymate.data.dto.finance.response.NewsDetailResponse
import com.moneymate.moneymate.data.dto.finance.response.NewsListResponse
import com.moneymate.moneymate.data.dto.finance.response.RentHouseLoanProductResponse
import com.moneymate.moneymate.data.dto.finance.response.SavingProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FinanceService {
    @GET("news/all")
    suspend fun getNewsList(): NewsListResponse

    @GET("news/detail")
    suspend fun getNewsDetail(
        @Query("publisher") publisher : String,
        @Query("category") category: String
    ) : NewsDetailResponse

    @GET("financial/products/deposit")
    suspend fun getDepositProducts(
        @Query("savingAmount") savingAmount: Int,
        @Query("period") period: Int,
        @Query("finGrpCode") finGrpCode: String,
        @Query("region") region: String,
        @Query("intrType") intrType: String,
        @Query("joinDeny") joinDeny: String,
        @Query("joinWay") joinWay: String
    ): DepositProductResponse

    @GET("financial/products/saving")
    suspend fun getSavingProducts(
        @Query("savingAmount") savingAmount: Int,
        @Query("period") period: Int,              // 1,3,6,12,24,36
        @Query("finGrpCode") finGrpCode: String,   // all, bank, savingsBank
        @Query("region") region: String,           // all 또는 CSV
        @Query("rsrvType") rsrvType: String,       // all, S(정액), F(자유)
        @Query("intrType") intrType: String,       // all, S(단리), M(복리)
        @Query("joinDeny") joinDeny: String,       // 1,2,3
        @Query("joinWay") joinWay: String          // all 또는 CSV
    ): SavingProductResponse

    @GET("financial/products/mortgage-loan")
    suspend fun getMortgageLoanProducts(
        @Query("mrtg_type") mrtgType: String,
        @Query("finGrpCode") finGrpCode: String,
        @Query("region") region: String,
        @Query("rpayType") rpayType: String,
        @Query("lendRateType") lendRateType: String,
        @Query("joinWay") joinWay: String
    ): MortgageLoanProductResponse

    @GET("financial/products/rent-house-loan")
    suspend fun getRentHouseLoanProducts(
        @Query("finGrpCode") finGrpCode: String,
        @Query("region") region: String,
        @Query("rpayType") rpayType: String,
        @Query("lendRateType") lendRateType: String,
        @Query("joinWay") joinWay: String
    ): RentHouseLoanProductResponse

    @GET("financial/products/credit-loan")
    suspend fun getCreditLoanProducts(
        @Query("finGrpCode") finGrpCode: String,
        @Query("region") region: String,
        @Query("crdtPrdtType") crdtPrdtType: String,
        @Query("crdtLendRateType") crdtLendRateType: String,
        @Query("joinWay") joinWay: String
    ): CreditLoanProductResponse
}