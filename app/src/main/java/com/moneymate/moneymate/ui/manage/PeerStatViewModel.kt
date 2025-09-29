package com.moneymate.moneymate.ui.manage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.manage.response.PeerStatResponse
import com.moneymate.moneymate.data.repository.ManageRepository
import com.moneymate.moneymate.ui.manage.component.PeerStatData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeerStatViewModel @Inject constructor(
    private val manageRepository: ManageRepository
) : ViewModel() {
    //총자산조회 결과
    private val _totalAsset = MutableStateFlow<Long>(0)
    val totalAsset = _totalAsset.asStateFlow()

    private val _peerAssetStat = MutableStateFlow<PeerStatData>(PeerStatData("",0,0))
    val peerAssetStat = _peerAssetStat.asStateFlow()
    private val _peerConsumptionStat = MutableStateFlow<List<PeerStatData>>(emptyList())
    val peerConsumptionStat = _peerConsumptionStat.asStateFlow()
    private val _peerIncomeStat = MutableStateFlow<List<PeerStatData>>(emptyList())
    val peerIncomeStat = _peerIncomeStat.asStateFlow()

    init {
        getTotalAsset()
        getPeerAssetStat(20)
        getPeerConsumptionStat(20)
        getPeerIncomeStat(20)
    }

    fun getTotalAsset() {
        viewModelScope.launch {
            try {
                _totalAsset.value = manageRepository.getTotalAssetPrice()
                Log.d("totalAsset", "${_totalAsset.value}")
            } catch (e: Exception) {
                Log.d("totalAsset", "$e")
            }
        }
    }

    fun getPeerAssetStat(
        age: Int,
        year: Int = 2024
    ) {
        viewModelScope.launch {
            manageRepository.getPeerAssetStat(age = age, year = year)
                .onSuccess { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 성공")
                    response.data
                        .filter { it.c2 == "자산" }
                        .let { data ->
                            val average = data.find { it.itemName == "평균" }?.value?.toLong() ?: 0L
                            val median = data.find { it.itemName == "중앙값" }?.value?.toLong() ?: 0L
                            _peerAssetStat.value = PeerStatData(
                                statName = "자산",
                                average = average,
                                median = median
                            )
                        }
                }
                .onFailure { response ->
                    Log.d("PeerStatViewModel", "자산 통계 조회 실패: ${response.message}")
                }
        }
    }

    fun getPeerConsumptionStat(
        age: Int,
        year: Int = 2024
    ) {
        viewModelScope.launch {
            manageRepository.getPeerConsumptionStat(age = age, year = year)
                .onSuccess { response ->
                    Log.d("PeerStatViewModel", "소비 통계 조회 성공")
                    val consumptionStats = response.data
                        .filter { it.itemName == "평균" }
                        .map { data ->
                            PeerStatData(
                                statName = data.c2,
                                average = (data.value ?: 0.0).toLong(),
                                median = 0
                            )
                        }
                    _peerConsumptionStat.value = consumptionStats
                }
                .onFailure { response ->
                    Log.d("PeerStatViewModel", "소비 통계 조회 실패: ${response.message}")
                }
        }
    }

    fun getPeerIncomeStat(
        age: Int,
        year: Int = 2024
    ) {
        viewModelScope.launch {
            manageRepository.getPeerIncomeStat(age = age, year = year)
                .onSuccess { response ->
                    Log.d("PeerStatViewModel", "소득 통계 조회 성공")
                    val incomeStats = response.data
                        .filter { it.itemName == "평균" && it.c2 != "사업소득"}
                        .map { data ->
                            PeerStatData(
                                statName = data.c2,
                                average = (data.value ?: 0.0).toLong(),
                                median = 0
                            )
                        }
                    _peerIncomeStat.value = incomeStats
                }
                .onFailure { response ->
                    Log.d("PeerStatViewModel", "소득 통계 조회 실패: ${response.message}")
                }
        }
    }
}