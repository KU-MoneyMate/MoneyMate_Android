package com.moneymate.moneymate.util.insight

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton

private val Context.insightDataStore by preferencesDataStore(name = "insight_cache")

@Singleton
class InsightCacheManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val PORTFOLIO_INSIGHT = stringPreferencesKey("portfolio_insight")
        private val PORTFOLIO_INSIGHT_TIMESTAMP = stringPreferencesKey("portfolio_insight_timestamp")
    }

    /**
     * 저장된 포트폴리오 인사이트를 가져옵니다.
     */
    fun getPortfolioInsight(): Flow<String?> {
        return context.insightDataStore.data.map { preferences ->
            preferences[PORTFOLIO_INSIGHT]
        }
    }

    /**
     * 포트폴리오 인사이트가 저장된 시간을 가져옵니다.
     */
    fun getPortfolioInsightTimestamp(): Flow<String?> {
        return context.insightDataStore.data.map { preferences ->
            preferences[PORTFOLIO_INSIGHT_TIMESTAMP]
        }
    }

    /**
     * 포트폴리오 인사이트를 저장합니다.
     * @param insight 인사이트 내용
     * @param timestamp 저장 시간 (기본값: 현재 시간)
     */
    suspend fun savePortfolioInsight(insight: String, timestamp: String) {
        context.insightDataStore.edit { preferences ->
            preferences[PORTFOLIO_INSIGHT] = insight
            preferences[PORTFOLIO_INSIGHT_TIMESTAMP] = timestamp
        }
    }

    /**
     * 저장된 포트폴리오 인사이트를 삭제합니다.
     */
    suspend fun clearPortfolioInsight() {
        context.insightDataStore.edit { preferences ->
            preferences.remove(PORTFOLIO_INSIGHT)
            preferences.remove(PORTFOLIO_INSIGHT_TIMESTAMP)
        }
    }

    /**
     * 타임스탬프를 포맷팅된 문자열로 반환합니다.
     */
    fun formatTimestamp(timestamp: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        return timestamp.format(formatter)
    }
}

