package com.moneymate.moneymate.ui.manage

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.data.dto.manage.response.Asset
import com.moneymate.moneymate.data.dto.manage.response.AssetStatHistoryData
import com.moneymate.moneymate.data.dto.manage.response.SpendingStatsData
import com.moneymate.moneymate.data.repository.ManageRepository
import com.moneymate.moneymate.util.API_DATE_FMT
import com.moneymate.moneymate.util.endOfMonth
import com.moneymate.moneymate.util.startOfMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ManageViewModel @Inject constructor(
    private val manageRepository: ManageRepository
): ViewModel() {
    //총자산조회 결과
    private val _totalAsset = MutableStateFlow<Long?>(null)
    val totalAsset = _totalAsset.asStateFlow()

    // 은퇴 시뮬레이션 입력값들
    private val _age = MutableStateFlow("40")
    val age = _age.asStateFlow()
    private val _retireAge = MutableStateFlow("55")
    val retireAge = _retireAge.asStateFlow()
    private val _endAge = MutableStateFlow("90")
    val endAge = _endAge.asStateFlow()
    private val _annualIncome = MutableStateFlow("50000000")
    val annualIncome = _annualIncome.asStateFlow()
    private val _annualExpense = MutableStateFlow("30000000")
    val annualExpense = _annualExpense.asStateFlow()
    private val _pensionPerYear = MutableStateFlow("24000000")
    val pensionPerYear = _pensionPerYear.asStateFlow()
    private val _accounts = MutableStateFlow("20000000")
    val accounts = _accounts.asStateFlow()
    private val _realEstate = MutableStateFlow("50000000")
    val realEstate = _realEstate.asStateFlow()
    private val _stocks = MutableStateFlow("30000000")
    val stocks = _stocks.asStateFlow()
    private val _assetReturnRate = MutableStateFlow("5")
    val assetReturnRate = _assetReturnRate.asStateFlow()
    private val _incomeGrowthRate = MutableStateFlow("3")
    val incomeGrowthRate = _incomeGrowthRate.asStateFlow()
    private val _inflationRate = MutableStateFlow("2")
    val inflationRate = _inflationRate.asStateFlow()
    private val _pensionStartAge = MutableStateFlow("65")
    val pensionStartAge = _pensionStartAge.asStateFlow()
    private val _consumptionDropAge = MutableStateFlow("65")
    val consumptionDropAge = _consumptionDropAge.asStateFlow()
    private val _consumptionDropRate = MutableStateFlow("20")
    val consumptionDropRate = _consumptionDropRate.asStateFlow()
    private val _crashCycle = MutableStateFlow("10")
    val crashCycle = _crashCycle.asStateFlow()
    private val _crashImpactRate = MutableStateFlow("15")
    val crashImpactRate = _crashImpactRate.asStateFlow()

    // 입력값 업데이트 함수들
    fun updateAge(value: String) { _age.value = value }
    fun updateRetireAge(value: String) { _retireAge.value = value }
    fun updateEndAge(value: String) { _endAge.value = value }
    fun updateAnnualIncome(value: String) { _annualIncome.value = value }
    fun updateAnnualExpense(value: String) { _annualExpense.value = value }
    fun updatePensionPerYear(value: String) { _pensionPerYear.value = value }
    fun updateAssetReturnRate(value: String) { _assetReturnRate.value = value }
    fun updateIncomeGrowthRate(value: String) { _incomeGrowthRate.value = value }
    fun updateInflationRate(value: String) { _inflationRate.value = value }
    fun updatePensionStartAge(value: String) { _pensionStartAge.value = value }
    fun updateConsumptionDropAge(value: String) { _consumptionDropAge.value = value }
    fun updateConsumptionDropRate(value: String) { _consumptionDropRate.value = value }
    fun updateCrashCycle(value: String) { _crashCycle.value = value }
    fun updateCrashImpactRate(value: String) { _crashImpactRate.value = value }
    // 시뮬레이션 결과 전체 응답을 담는 상태
    private val _retireResult = MutableStateFlow<List<Asset>>(emptyList())
    val retireResult = _retireResult.asStateFlow()

    // 자산 변동 조회 결과
    private val _assetStatHistory = MutableStateFlow<List<AssetStatHistoryData>>(emptyList())
    val assetStatHistory = _assetStatHistory.asStateFlow()

    // 소비 통계 조회 결과 (raw data 보관)
    private val _spendingStat = MutableStateFlow<SpendingStatsData?>(null)
    val spendingStat = _spendingStat.asStateFlow()

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

    fun updateTotalAsset(value: String) {
        _totalAsset.value = value.replace(",", "").toLongOrNull()
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

    // 소비 통계 조회
    fun getSpendingStatistics(month: LocalDate) {
        val startDate = month.startOfMonth().format(API_DATE_FMT)
        val endDate   = month.endOfMonth().format(API_DATE_FMT)

        viewModelScope.launch {
            manageRepository.getSpendingStatistics(startDate, endDate)
                .onSuccess { response ->
                    Log.d("ManageViewModel", "소비 통계 조회 성공: ${response.data.startDate} ~ ${response.data.endDate}")
                    _spendingStat.value = response.data
                }
                .onFailure { t ->
                    Log.d("ManageViewModel", "소비 통계 조회 실패: ${t.message}")
                }
        }
    }
}