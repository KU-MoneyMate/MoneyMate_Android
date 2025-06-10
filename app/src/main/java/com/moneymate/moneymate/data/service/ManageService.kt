package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
import com.moneymate.moneymate.data.dto.manage.response.TotalAssetResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ManageService {
    //은퇴시뮬레이션
    @POST("asset/retirement/simulate")
    suspend fun getRetireSimulation(
        @Body request: RetireInputRequest
    ): List<Asset>

    //은퇴시뮬레이션 - 총자산 조회
    @GET("asset/total-price")
    suspend fun getTotalAssetPrice():TotalAssetResponse
}