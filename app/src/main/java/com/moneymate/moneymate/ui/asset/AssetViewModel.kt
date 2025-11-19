package com.moneymate.moneymate.ui.asset

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.data.dto.asset.response.AssetInfo
import com.moneymate.moneymate.data.dto.account.response.TransactionInfo
import com.moneymate.moneymate.data.dto.asset.response.StockInfo
import com.moneymate.moneymate.data.repository.AssetRepository
import com.moneymate.moneymate.data.repository.StockIconRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(
    private val assetRepository: AssetRepository,
    private val stockIconRepository: StockIconRepository
) : ViewModel() {
    // 홈 화면에서 조회할 전체 계좌 정보
    private val _totalAccounts = MutableStateFlow<List<AccountInfo>>(emptyList())
    val totalAccounts = _totalAccounts.asStateFlow()
    // 홈 화면에서 조회할 전체 자산 정보
    private val _totalAssets= MutableStateFlow<List<AssetInfo>>(emptyList())
    val totalAssets = _totalAssets.asStateFlow()
    // 홈 화면에서 조회할 전체 주식 정보
    private val _totalStocks = MutableStateFlow<List<StockInfo>>(emptyList())
    val totalStocks = _totalStocks.asStateFlow()
    // 계좌 거래 내역 정보
    private val _transactionInfoHistory = MutableStateFlow<List<TransactionInfo>>(emptyList())
    val transactionHistory = _transactionInfoHistory.asStateFlow()
    // 주식 로딩 상태
    private val _isStocksLoading = MutableStateFlow(false)
    val isStocksLoading = _isStocksLoading.asStateFlow()

    init {
        getTotalAccountList()
        getAssetList()
        getStockList()
    }

    // 전체 계좌 정보 조회
    fun getTotalAccountList() {
        viewModelScope.launch {
            assetRepository.getAccountList()
                .onSuccess { response ->
                    _totalAccounts.value = response.data.account
                    Log.d("AssetViewModel", response.data.toString())
                }
                .onFailure { response ->
                    response.message?.let { Log.d("AssetViewModel", response.message.toString()) }
                }
        }
    }

    // 계좌 내역 조회
    fun getTransactionHistory(
        uid: String,
        startDate: String,
        endDate: String
    ){
        viewModelScope.launch {
            assetRepository.getTransactionHistory(uid = uid, startDate = startDate, endDate = endDate)
                .onSuccess { response ->
                    _transactionInfoHistory.value = response.data.transaction.sortedByDescending { it.date }
                    Log.d("AssetViewModel", response.data.transaction.toString())
                }
                .onFailure { response ->
                    response.message?.let { Log.d("AssetViewModel", "계좌 내역 조회 실패") }
                }
        }
    }

    // 전체 자산 조회
    fun getAssetList() {
        viewModelScope.launch {
            assetRepository.getAssetList()
                .onSuccess { response ->
                    _totalAssets.value = response.data.asset
                    Log.d("AssetViewModel", "자산 조회 성공: ${response.data.asset}")
                }
                .onFailure { response ->
                    response.message?.let { Log.d("AssetViewModel", "자산 조회 실패") }
                }
        }
    }

    // 자산 등록
    fun registerAsset(
        name: String,
        price: Long
    ){
        viewModelScope.launch {
            assetRepository.registerAsset(
                name = name,
                price = price
            ).onSuccess { response ->
                Log.d("AssetViewModel", response.message)
                getAssetList() // 자산 업데이트
            }.onFailure { response ->
                response.message?.let { Log.d("AssetViewModel", it) }
            }
        }
    }

    fun getStockList(){
        viewModelScope.launch {
            _isStocksLoading.value = true
            assetRepository.getStockList()
                .onSuccess { response ->
                    _totalStocks.value = response.data
                    _isStocksLoading.value = false
                    Log.d("AssetViewModel", "주식 목록 조회 성공: ${response.data.size}개")
                    response.data.forEach { stock ->
                        Log.d("AssetViewModel", "주식 정보: ${stock.stockName} (${stock.ticker})")
                    }
                }
                .onFailure { response ->
                    _isStocksLoading.value = false
                    response.message?.let { Log.e("AssetViewModel", "주식 조회 실패: $it") }
                }
        }
    }

    // 주식 아이콘 URL 조회
    fun getStockIconUrl(ticker: String): String {
        val url = stockIconRepository.getStockIconUrl(ticker)
        Log.d("AssetViewModel", "ViewModel에서 아이콘 URL 요청 [$ticker]: $url")
        return url
    }
}