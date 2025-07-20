package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.finance.response.NewsDetailResponse
import com.moneymate.moneymate.data.dto.finance.response.NewsListResponse
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
}