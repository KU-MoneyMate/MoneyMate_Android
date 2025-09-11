package com.moneymate.moneymate.ui.finance

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
    val uiState: StateFlow<MarketInfoUiState> = _uiState.asStateFlow()

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
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                }
        }
    }

    private fun loadExchangeRates() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            domesticStockRepository.getExchangeRate()
                .onSuccess { rates ->
                    _uiState.update { it.copy(
                        exchangeRates = rates,
                        isLoading = false
                    ) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
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
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                }

            // Load rising stocks
            domesticStockRepository.getDomesticMarketRising(1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(domesticMarketRising = response) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                }

            // Load falling stocks
            domesticStockRepository.getDomesticMarketFalling(1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(
                        domesticMarketFalling = response,
                        isLoading = false
                    ) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
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
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                }

            // Load rising stocks
            foreignStockRepository.getForeignMarketRising(market, 1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(foreignMarketRising = response) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message) }
                }

            // Load falling stocks
            foreignStockRepository.getForeignMarketFalling(market, 1, 20)
                .onSuccess { response ->
                    _uiState.update { it.copy(
                        foreignMarketFalling = response,
                        isLoading = false
                    ) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(
                        error = e.message,
                        isLoading = false
                    ) }
                }
        }
    }
}