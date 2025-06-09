package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.account.request.TransactionHistoryRequest
import com.moneymate.moneymate.data.dto.account.response.AccountListResponse
import com.moneymate.moneymate.data.dto.asset.response.AssetListResponse
import com.moneymate.moneymate.data.dto.account.response.TransactionHistoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AssetService {
    // 계좌 내역 조회
    @POST("account/get-transaction")
    suspend fun getTransactionHistory(
        @Body request: TransactionHistoryRequest
    ): TransactionHistoryResponse

    // 전체 계좌 정보 조회
    @GET("account/get-list")
    suspend fun getAccountList(): AccountListResponse

    // 전체 자산 조회
    @GET("asset/get")
    suspend fun getAssetList(): AssetListResponse
}