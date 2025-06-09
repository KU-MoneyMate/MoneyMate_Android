package com.moneymate.moneymate.ui.manage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
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

    // 시뮬레이션 결과 전체 응답을 담는 상태
    val _retireResult = MutableStateFlow<List<Asset>>(emptyList())
    val retireResult = _retireResult.asStateFlow()

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
}