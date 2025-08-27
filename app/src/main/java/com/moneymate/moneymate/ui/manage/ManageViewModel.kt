package com.moneymate.moneymate.ui.manage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
import com.moneymate.moneymate.data.dto.manage.response.AssetStatHistoryData
import com.moneymate.moneymate.data.repository.ManageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManageViewModel @Inject constructor(
    private val manageRepository: ManageRepository
): ViewModel() {
    //총자산조회 결과
    private val _totalAsset = mutableStateOf<Long?>(null)
    val totalAsset: State<Long?> = _totalAsset
    // 시뮬레이션 결과 전체 응답을 담는 상태
    private val _retireResult = MutableStateFlow<List<Asset>>(emptyList())
    val retireResult = _retireResult.asStateFlow()

    // 자산 변동 조회 결과
    private val _assetStatHistory = MutableStateFlow<List<AssetStatHistoryData>>(emptyList())
    val assetStatHistory = _assetStatHistory.asStateFlow()

    init {
        getTotalAsset()
        getAssetStatsHistory("total")
    }

    //총자산 조회
    fun getTotalAsset() {
        viewModelScope.launch {
            try {
                _totalAsset.value = manageRepository.getTotalAssetPrice()
                Log.d("totalAsset", "${_totalAsset.value}" )
            } catch (e: Exception) {
                Log.d("totalAsset", "${e}")
                // 에러 처리
            }
        }
    }

    // 시뮬레이션 요청
    fun postRetirementSimulation(request: RetireInputRequest) {
        viewModelScope.launch {
            manageRepository.postRetireSimulation(request)
                .onSuccess { response ->
                    _retireResult.value = response
                    Log.d("RetireViewModel", "시뮬레이션 성공: ${response}")
                }
                .onFailure { throwable ->
                    Log.d("RetireViewModel", "시뮬레이션 실패: ${throwable.message}")
                }
        }
    }

    //뒤로가기를 위한 초기화 함수
    fun clearRetireResult() {
        _retireResult.value = emptyList()
    }

    // 자산 변동 조회
    fun getAssetStatsHistory(category: String) {
        viewModelScope.launch {
            manageRepository.getAssetStatsHistory(category)
                .onSuccess { response ->
                    Log.d("ManageViewModel", "자산 변동 조회 성공")
                    _assetStatHistory.value = response.data
                }
                .onFailure { response ->
                    Log.d("ManageViewModel", "자산 변동 조회 실패: ${response.message}")
                }
        }
    }
}