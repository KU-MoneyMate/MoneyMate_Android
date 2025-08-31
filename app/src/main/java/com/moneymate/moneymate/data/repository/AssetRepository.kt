package com.moneymate.moneymate.data.repository

import com.moneymate.moneymate.data.dto.account.request.TransactionHistoryRequest
import com.moneymate.moneymate.data.dto.asset.request.AssetRegisterRequest
import com.moneymate.moneymate.data.service.AssetService

class AssetRepository(
    private val assetService: AssetService
) {
    // 전체 계좌 정보 조회
    suspend fun getAccountList() = runCatching { assetService.getAccountList() }

    // 계좌 내역 조회
    suspend fun getTransactionHistory(
        uid: String,
        startDate: String,
        endDate: String
    ) = runCatching {
        assetService.getTransactionHistory(
            TransactionHistoryRequest(
                accountUid = uid,
                startDate = startDate,
                endDate = endDate
            )
        )
    }

    // 전체 자산 조회
    suspend fun getAssetList() = runCatching { assetService.getAssetList() }

    // 자산 등록
    suspend fun registerAsset(
        name: String,
        price: Long
    ) = runCatching {
        assetService.registerAsset(
            AssetRegisterRequest(
                name = name,
                price = price,
            )
        )
    }

    // 주식 조회
    suspend fun getStockList() = runCatching { assetService.getStockList() }
}