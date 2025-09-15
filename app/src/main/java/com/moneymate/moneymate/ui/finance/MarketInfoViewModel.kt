package com.moneymate.moneymate.ui.finance

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.finance.response.market.DomesticStockResponse
import com.moneymate.moneymate.data.dto.finance.response.market.ExchangeRateResponse
import com.moneymate.moneymate.data.dto.finance.response.market.ForeignStockResponse
import com.moneymate.moneymate.data.dto.finance.response.market.MarketIndexResponse
import com.moneymate.moneymate.data.repository.DomesticStockRepository
import com.moneymate.moneymate.data.repository.ForeignStockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MarketInfoUiState(
    val isLoading: Boolean = false,
    val marketIndexes: List<MarketIndexResponse> = emptyList(),
    val exchangeRates: ExchangeRateResponse? = null,
    val domesticMarketCap: DomesticStockResponse? = null,
    val domesticMarketRising: DomesticStockResponse? = null,
    val domesticMarketFalling: DomesticStockResponse? = null,
    val foreignMarketCap: ForeignStockResponse? = null,
    val foreignMarketRising: ForeignStockResponse? = null,
    val foreignMarketFalling: ForeignStockResponse? = null,
    val error: String? = null
)

@HiltViewModel
class MarketInfoViewModel @Inject constructor(
    private val domesticStockRepository: DomesticStockRepository,
    private val foreignStockRepository: ForeignStockRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MarketInfoUiState())
    val uiState = _uiState.asStateFlow()

    val exchangeRatesList = listOf("USD", "JPY", "EUR", "GBP", "CNY", "HKD", "CHF", "CAD", "AUD", "TWD")

    init {
        loadMarketIndexes()
        loadExchangeRates()
    }

    fun loadMarketData(market: String, isDomestic: Boolean) {
        if (isDomestic) {
            loadDomesticMarketData()
        } else {
            loadForeignMarketData(market)
        }
    }

    private fun loadMarketIndexes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            foreignStockRepository.getMarketIndexes()
                .onSuccess { indexes ->
                    _uiState.update { it.copy(
                        marketIndexes = indexes,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "지수 조회 성공")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "지수 조회 실패 : ${e.message}")
                }
        }
    }

    private fun loadExchangeRates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            domesticStockRepository.getExchangeRate()
                .onSuccess { rates ->
                    val filteredRates = rates.copy(
                        result = rates.result
                            .filter { rate -> exchangeRatesList.any { code -> rate.name.contains(code) } }
                            .sortedBy { rate -> exchangeRatesList.indexOfFirst { code -> rate.name.contains(code) } }
                    )
                    _uiState.update { it.copy(
                        exchangeRates = filteredRates,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "환율 조회 성공")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "환율 조회 실패 : ${e.message}")
                }
        }
    }

    private fun loadDomesticMarketData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Load market cap
            domesticStockRepository.getDomesticMarketCap(1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(domesticMarketCap = response) }
                    Log.d("MarketInfoViewModel", "국내 주식 시가총액 : $response")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                    Log.d("MarketInfoViewModel", "국내 주식 시가총액 로드 실패 : ${e.message}")
                }

            // Load rising stocks
            domesticStockRepository.getDomesticMarketRising(1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(domesticMarketRising = response) }
                    Log.d("MarketInfoViewModel", "국내 주식 상승 : $response")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                    Log.d("MarketInfoViewModel", "국내 주식 상승 로드 실패 : ${e.message}")
                }

            // Load falling stocks
            domesticStockRepository.getDomesticMarketFalling(1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(
                        domesticMarketFalling = response,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "국내 주식 하락 : $response")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "국내 주식 하락 로드 실패 : ${e.message}")
                }
        }
    }

    private fun loadForeignMarketData(market: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Load market cap
            foreignStockRepository.getForeignMarketCap(market, 1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(foreignMarketCap = response) }
                    Log.d("MarketInfoViewModel", "$market 시가총액 : $response")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                    Log.d("MarketInfoViewModel", "$market 시가총액 로드 실패 : ${e.message}")
                }

            // Load rising stocks
            foreignStockRepository.getForeignMarketRising(market, 1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(foreignMarketRising = response) }
                    Log.d("MarketInfoViewModel", "$market 상승 : $response")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                    Log.d("MarketInfoViewModel", "$market 상승 로드 실패 : ${e.message}")
                }

            // Load falling stocks
            foreignStockRepository.getForeignMarketFalling(market, 1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(
                        foreignMarketFalling = response,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "$market 하락 : $response")
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                    Log.d("MarketInfoViewModel", "$market 하락 로드 실패 : ${e.message}")
                }
        }
    }
}