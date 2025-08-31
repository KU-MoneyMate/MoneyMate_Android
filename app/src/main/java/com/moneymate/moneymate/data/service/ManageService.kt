package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.manage.response.AssetStatHistoryResponse
import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
import com.moneymate.moneymate.data.dto.manage.response.SpendingStatsResponse
import com.moneymate.moneymate.data.dto.manage.response.TotalAssetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ManageService {
    //은퇴시뮬레이션
    @POST("asset/retirement/simulate")
    suspend fun getRetireSimulation(
        @Body request: RetireInputRequest
    ): List<Asset>

    //은퇴시뮬레이션 - 총자산 조회
    @GET("asset/total-price")
    suspend fun getTotalAssetPrice():TotalAssetResponse

    // 자산 변동 조회
    @GET("asset/stats/history")
    suspend fun getAssetStatsHistory(
        @Query("category") category: String
    ): AssetStatHistoryResponse

    // 소비 통계 조회
    @GET("asset/stats/consumption")
    suspend fun getSpendingStatistics(
        @Query("startDay") startDay : String,
        @Query("endDay") endDay : String
    ) : SpendingStatsResponse
}