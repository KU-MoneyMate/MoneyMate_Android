package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.finance.response.NewsListResponse
import retrofit2.http.GET

interface FinanceService {
    @GET("news/all")
    suspend fun getNewsList(): NewsListResponse
}