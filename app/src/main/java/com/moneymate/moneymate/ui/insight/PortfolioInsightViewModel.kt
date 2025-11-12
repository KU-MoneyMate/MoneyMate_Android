package com.moneymate.moneymate.ui.insight

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.repository.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PortfolioInsightUiState(
    val isLoading: Boolean = false,
    val insight: String = "",
    val errorMessage: String? = null
)

@HiltViewModel
class PortfolioInsightViewModel@Inject constructor(
    private val repository: FinanceRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(PortfolioInsightUiState())
    val uiState: StateFlow<PortfolioInsightUiState> = _uiState.asStateFlow()

    init {
        getPortfolioInsight()
    }

    fun getPortfolioInsight() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getPortfolioInsight()
                .onSuccess { response ->
                    val insight = response.data
                    _uiState.update { it.copy(isLoading = false, insight = insight) }
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