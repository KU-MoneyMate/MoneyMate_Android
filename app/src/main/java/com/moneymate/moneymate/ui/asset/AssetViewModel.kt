package com.moneymate.moneymate.ui.asset

import android.util.Log
import androidx.compose.ui.text.resolveDefaults
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.data.dto.account.response.AssetInfo
import com.moneymate.moneymate.data.dto.account.response.Transaction
import com.moneymate.moneymate.data.dto.account.response.TransactionHistoryResponse
import com.moneymate.moneymate.data.repository.AssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(
    private val assetRepository: AssetRepository
) : ViewModel() {
    // 홈 화면에서 조회할 전체 계좌 정보
    private val _totalAccounts = MutableStateFlow<List<AccountInfo>?>(emptyList())
    val totalAccounts = _totalAccounts.asStateFlow()
    // 홈 화면에서 조회할 전체 자산 정보
    private val _totalAssets= MutableStateFlow<List<AssetInfo>?>(emptyList())
    val totalAssets = _totalAssets.asStateFlow()
    // 계좌 거래 내역 정보
    private val _transactionHistory = MutableStateFlow<List<Transaction>?>(emptyList())


    // 전체 계좌 정보 조회
    fun getTotalAccountList() {
        viewModelScope.launch {
            assetRepository.getAccountList()
                .onSuccess { response ->
                    _totalAccounts.value = response.data
                }
                .onFailure { response ->
                    response.message?.let { Log.d("AssetViewModel", "게좌 조회 실패") }
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
                    _transactionHistory.value = response.data
                    Log.d("AssetViewModel", response.data.size.toString())
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
                    _totalAssets.value = response.data
                }
                .onFailure { response ->
                    response.message?.let { Log.d("AssetViewModel", "자산 조회 실패") }
                }
        }
    }
}