package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
import com.moneymate.moneymate.data.dto.manage.response.PeerStatResponse
import com.moneymate.moneymate.data.dto.manage.response.SpendingStatsResponse
import com.moneymate.moneymate.data.service.ManageService
import retrofit2.http.GET
import retrofit2.http.Query

class ManageRepository(
    private val manageService: ManageService
) {
    // 은퇴시뮬레이션 결과 요청
    suspend fun postRetireSimulation(request: RetireInputRequest): Result<List<Asset>> = runCatching {
        manageService.getRetireSimulation(request)
    }

    //은퇴시뮬레이션 - 총자산조회
    suspend fun getTotalAssetPrice(): Long {
        return manageService.getTotalAssetPrice().data
    }

    // 자산 변동 조회
    suspend fun getAssetStatsHistory(category: String) = runCatching { 
        manageService.getAssetStatsHistory(category)
    }

    // 소비 통계 조회
    suspend fun getSpendingStatistics(startDay: String, endDay: String) = runCatching {
        manageService.getSpendingStatistics(startDay, endDay)
    }

    // 또래 자산 통계 조회
    suspend fun getPeerAssetStat(age: Int, year: Int) = runCatching {
        manageService.getPeerAssetStat(age = age, year = year)
    }

    // 또래 소비 통계 조회
    suspend fun getPeerConsumptionStat(age: Int, year: Int) = runCatching {
        manageService.getPeerConsumptionStat(age = age, year = year)
    }

    // 또래 소득 통계 조회
    suspend fun getPeerIncomeStat(age: Int, year: Int) = runCatching {
        manageService.getPeerIncomeStat(age = age, year = year)
    }
}