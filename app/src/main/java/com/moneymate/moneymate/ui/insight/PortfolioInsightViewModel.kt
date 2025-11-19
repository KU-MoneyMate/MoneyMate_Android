package com.moneymate.moneymate.ui.insight

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.repository.FinanceRepository
import com.moneymate.moneymate.util.insight.InsightCacheManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

data class PortfolioInsightUiState(
    val isLoading: Boolean = false,
    val insight: String = "",
    val insightDate: String = "",
    val errorMessage: String? = null
)

@HiltViewModel
class PortfolioInsightViewModel@Inject constructor(
    private val repository: FinanceRepository,
    private val insightCacheManager: InsightCacheManager
): ViewModel() {
    private val _uiState = MutableStateFlow(PortfolioInsightUiState())
    val uiState: StateFlow<PortfolioInsightUiState> = _uiState.asStateFlow()

    init {
        loadCachedInsight()
    }

    /**
     * 캐시된 인사이트를 먼저 로드합니다.
     */
    private fun loadCachedInsight() {
        viewModelScope.launch {
            val cachedInsight = insightCacheManager.getPortfolioInsight().first()
            val cachedTimestamp = insightCacheManager.getPortfolioInsightTimestamp().first()
            
            if (cachedInsight != null && cachedTimestamp != null) {
                _uiState.update {
                    it.copy(
                        insight = cachedInsight,
                        insightDate = cachedTimestamp,
                        isLoading = false
                    ) 
                }
                Log.d("PortfolioInsightViewModel", "캐시된 포트폴리오 분석 로드 성공")
            } else {
                Log.d("PortfolioInsightViewModel", "캐시된 데이터가 없습니다.")
            }
        }
    }

    /**
     * 새로운 포트폴리오 인사이트를 요청합니다.
     */
    fun getPortfolioInsight() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getPortfolioInsight()
                .onSuccess { response ->
                    val insight = response.data.context
                    val timestamp = response.data.currentTime.substringBefore("T")

                    // 인사이트를 캐시에 저장
                    insightCacheManager.savePortfolioInsight(insight, timestamp)
                    
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            insight = insight,
                            insightDate = timestamp
                        ) 
                    }
                    Log.d("PortfolioInsightViewModel", "포트폴리오 분석 조회 성공")
                }
                .onFailure { response ->
                    val errorMessage = response.message ?: "포트폴리오 요약 조회 실패"
                    _uiState.update { it.copy(isLoading = false, errorMessage = errorMessage) }
                    Log.d("PortfolioInsightViewModel", "포트폴리오 분석 조회 실패")
                }
        }
    }
}