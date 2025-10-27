package com.moneymate.moneymate.data.repository

import android.util.Log
import com.moneymate.moneymate.BuildConfig

class StockIconRepository {
    // 주식 아이콘 URL 생성
    fun getStockIconUrl(ticker: String): String {
        val url = "${BuildConfig.STOCK_ICON_BASE_URL}stock_icons/$ticker.svg"
        Log.d("StockIconRepository", "생성된 아이콘 URL [$ticker]: $url")
        return url
    }
}