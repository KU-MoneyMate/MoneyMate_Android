package com.moneymate.moneymate.data.repository

import android.util.Log
import com.moneymate.moneymate.BuildConfig
import com.moneymate.moneymate.data.service.StockIconService
import okhttp3.ResponseBody

class StockIconRepository(
    private val stockIconService: StockIconService
) {
    // 주식 아이콘 URL 생성
    fun getStockIconUrl(ticker: String): String {
        val url = "${BuildConfig.STOCK_ICON_BASE_URL}stock_icons/$ticker.svg"
        Log.d("StockIconRepository","생성된 아이콘 URL [$ticker]: $url")
        return url
    }

    // 주식 아이콘 조회 - 티커를 기반으로 SVG 아이콘 다운로드 (필요시 사용)
    suspend fun getStockIcon(ticker: String): Result<ResponseBody> = 
        runCatching { stockIconService.getStockIcon(ticker) }
}