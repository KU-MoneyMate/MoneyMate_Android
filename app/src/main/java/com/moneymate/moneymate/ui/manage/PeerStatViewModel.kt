package com.moneymate.moneymate.ui.manage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.manage.response.PeerStatResponse
import com.moneymate.moneymate.data.repository.ManageRepository
import com.moneymate.moneymate.ui.manage.component.PeerStatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeerStatViewModel  @Inject constructor(
    private val manageRepository: ManageRepository
): ViewModel() {
    //총자산조회 결과
    private val _totalAsset = MutableStateFlow<Long?>(null)
    val totalAsset = _totalAsset.asStateFlow()

    private val _peerAssetStat = MutableStateFlow<PeerStatData?>(null)
    val peerAssetStat = _peerAssetStat.asStateFlow()
    private val _peerConsumptionStat = MutableStateFlow<PeerStatData?>(null)
    val peerConsumptionStat = _peerConsumptionStat.asStateFlow()
    private val _peerIncomeStat = MutableStateFlow<PeerStatData?>(null)
    val peerIncomeStat = _peerIncomeStat.asStateFlow()

    init {
        getPeerAssetStat(20)
        getPeerConsumptionStat(20)
        getPeerIncomeStat(20)
    }

    fun getPeerAssetStat(
        age: Int,
        year: Int = 2024
    ){
        viewModelScope.launch {
            manageRepository.getPeerAssetStat(age = age, year = year)
                .onSuccess { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 성공")
                }
                .onFailure { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 실패: ${response.message}")
                }
        }
    }

    fun getPeerConsumptionStat(
        age: Int,
        year: Int = 2024
    ){
        viewModelScope.launch {
            manageRepository.getPeerConsumptionStat(age = age, year = year)
                .onSuccess { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 성공")
                }
                .onFailure { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 실패: ${response.message}")
                }
        }
    }

    fun getPeerIncomeStat(
        age: Int,
        year: Int = 2024
    ){
        viewModelScope.launch {
            manageRepository.getPeerIncomeStat(age = age, year = year)
                .onSuccess { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 성공")
                }
                .onFailure { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 실패: ${response.message}")
                }
        }
    }
}