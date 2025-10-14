package com.moneymate.moneymate.ui.insight

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.insight.response.NewsSummaryData
import com.moneymate.moneymate.data.repository.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NewsInsightUiState(
    val isLoading: Boolean = false,
    val newsList: List<NewsSummaryData> = emptyList(),
    val errorMessage: String? = null
)

@HiltViewModel
class NewsInsightViewModel @Inject constructor(
    private val repository: FinanceRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(NewsInsightUiState())
    val uiState: StateFlow<NewsInsightUiState> = _uiState.asStateFlow()

    init {
        getNewsInsight()
    }

    fun getNewsInsight() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getNewsInsight()
                .onSuccess { response ->
                    val newsList = response.data
                    _uiState.update { it.copy(isLoading = false, newsList = newsList) }
                    Log.d("NewsInsightViewModel", "뉴스 요약 조회 성공")
                }
                .onFailure { response ->
                    val errorMessage = response.message ?: "뉴스 요약 조회 실패"
                    _uiState.update { it.copy(isLoading = false, errorMessage = errorMessage) }
                    Log.d("NewsInsightViewModel", errorMessage)
                }
        }
    }
}