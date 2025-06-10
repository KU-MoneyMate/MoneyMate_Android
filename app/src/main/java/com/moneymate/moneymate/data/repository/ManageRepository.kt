package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
import com.moneymate.moneymate.data.service.ManageService

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

}