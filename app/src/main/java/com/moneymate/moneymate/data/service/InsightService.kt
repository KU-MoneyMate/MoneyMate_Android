package com.moneymate.moneymate.data.service

import com.moneymate.moneymate.data.dto.insight.response.NewsInsightResponse
import com.moneymate.moneymate.data.dto.insight.response.PortfolioInsightResponse
import retrofit2.http.GET

interface InsightService {
    @GET("news/ai-summary")
    suspend fun getNewsInsight(): NewsInsightResponse

    @GET("portfolio/ai-summary")
    suspend fun getPortfolioInsight(): PortfolioInsightResponse
}

