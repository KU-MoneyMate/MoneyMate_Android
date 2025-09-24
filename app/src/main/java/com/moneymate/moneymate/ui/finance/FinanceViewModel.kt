package com.moneymate.moneymate.ui.finance

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.finance.NewsInfo
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.data.repository.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
): ViewModel() {

    private val _newsList = mutableStateOf<List<NewsInfo>>(emptyList())
    val newsList: State<List<NewsInfo>> = _newsList

    private val _categoryNewsList = mutableStateOf<List<NewsInfo>>(emptyList())
    val categoryNewsList: State<List<NewsInfo>> = _categoryNewsList

    private val _depositList = MutableStateFlow<List<DepositProductItemDto>>(emptyList())
    val depositList: StateFlow<List<DepositProductItemDto>> = _depositList.asStateFlow()

    private val _navigateToDepositList = MutableSharedFlow<Unit>()
    val navigateToDepositList = _navigateToDepositList.asSharedFlow()

    init {
        getNewsList()
    }

    fun getNewsList(){
        viewModelScope.launch {
            runCatching { financeRepository.getNewsList() }
                .onSuccess {
                    _newsList.value = it
                    Log.d("FinanceViewModel", "조회 성공")
                }
                .onFailure { response ->
                    response.message?.let { Log.d("FinanceViewModel", response.message.toString()) }
                }
        }
    }

    fun getCategoryNews(publisher : String, category : String) {
        viewModelScope.launch {
            runCatching { financeRepository.getCategoryNews(publisher, category) }
                .onSuccess {
                    _categoryNewsList.value = it
                    Log.d("FinanceViewModel", "조회 성공")
                }
                .onFailure { response ->
                    response.message?.let { Log.d("FinanceViewModel", response.message.toString()) }
                }
        }
    }

    fun getDepositProductsByLabels(
        savingAmount: Int,
        periodLabel: String?,           // "1개월" 등
        finGrpLabel: String,            // "전체"|"은행"|"저축은행"
        regions: Set<String>,           // 라벨들
        intrTypeLabel: String,          // "전체"|"단리"|"복리"
        joinDenyLabel: String,          // "제한없음"|"서민전용"|"일부제한"
        joinWayLabels: Set<String>      // 라벨들
    ) {
        val period = when (periodLabel) {
            "1개월" -> 1; "3개월" -> 3; "6개월" -> 6
            "12개월" -> 12; "24개월" -> 24; "36개월" -> 36
            else -> 0
        }
        val finGrpCode = when (finGrpLabel) {
            "전체" -> "all"; "은행" -> "bank"; "저축은행" -> "savingsBank"
            else -> "all"
        }
        val intrType = when (intrTypeLabel) {
            "전체" -> "all"; "단리" -> "S"; "복리" -> "M"
            else -> "all"
        }
        val joinDeny = when (joinDenyLabel) {
            "제한없음" -> "1"; "서민전용" -> "2"; "일부제한" -> "3"
            else -> "1"
        }

        fun regionLabelToCode(label: String) = when (label) {
            "전체" -> "all"; "서울" -> "seoul"; "부산" -> "busan"; "대구" -> "daegu"; "인천" -> "incheon"
            "광주" -> "gwangju"; "대전" -> "daejeon"; "울산" -> "ulsan"; "세종" -> "sejong"
            "경기" -> "gyeonggi"; "강원" -> "gangwon"; "충북" -> "chungbuk"; "충남" -> "chungnam"
            "전북" -> "jeonbuk"; "전남" -> "jeonnam"; "경북" -> "gyeongbuk"; "경남" -> "gyeongnam"
            "제주" -> "jeju"
            else -> "all"
        }

        fun joinWayLabelToCode(label: String) = when (label) {
            "전체" -> "all"; "영업점" -> "branch"; "인터넷" -> "internet"; "스마트폰" -> "smartphone"
            "모집인" -> "recruiter"; "전화(텔레뱅킹)" -> "telephone"; "기타" -> "others"
            else -> "all"
        }

        val regionCsv = regions.map { regionLabelToCode(it) }.toSet().joinToString(",")
        val joinWayCsv = joinWayLabels.map { joinWayLabelToCode(it) }.toSet().joinToString(",")

        viewModelScope.launch {
            runCatching {
                financeRepository.getDepositProducts(
                    savingAmount = savingAmount,
                    period = period,
                    finGrpCode = finGrpCode,
                    regionCsv = regionCsv,
                    intrType = intrType,
                    joinDeny = joinDeny,
                    joinWayCsv = joinWayCsv
                )

            }
                .onSuccess {
                    _depositList.value = it
                    Log.d("DEBUG_LOG", "ViewModel: StateFlow가 ${it.size}개의 아이템으로 업데이트됨")
                    Log.d("FinanceViewModel", "예금상품 조회 성공: ${it.size}개 상품")
                    _navigateToDepositList.emit(Unit)
                }
                .onFailure { e ->
                    Log.d("FinanceViewModel", "예금상품 조회 실패: ${e.message.orEmpty()}")
                }
        }
    }

}