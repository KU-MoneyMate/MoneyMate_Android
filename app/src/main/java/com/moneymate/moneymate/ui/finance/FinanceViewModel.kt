package com.moneymate.moneymate.ui.finance

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.finance.NewsInfo
import com.moneymate.moneymate.data.dto.finance.response.CreditLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.MortgageLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.RentHouseLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.SavingProductItemDto
import com.moneymate.moneymate.data.repository.FinanceRepository
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.ProductViewType
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

    private val _currentViewType = MutableStateFlow(ProductViewType.DEPOSIT)
    val currentViewType = _currentViewType.asStateFlow()

    fun setViewType(type: ProductViewType) {
        _currentViewType.value = type
    }

    private val _depositList = MutableStateFlow<List<DepositProductItemDto>>(emptyList())
    val depositList: StateFlow<List<DepositProductItemDto>> = _depositList.asStateFlow()

    private val _navigateToDepositList = MutableSharedFlow<Unit>()
    val navigateToDepositList = _navigateToDepositList.asSharedFlow()

    private val _savingList = MutableStateFlow<List<SavingProductItemDto>>(emptyList())
    val savingList: StateFlow<List<SavingProductItemDto>> = _savingList.asStateFlow()

    private val _navigateToSavingList = MutableSharedFlow<Unit>()
    val navigateToSavingList = _navigateToSavingList.asSharedFlow()

    private val _mortgageLoanList = MutableStateFlow<List<MortgageLoanProductItemDto>>(emptyList())
    val mortgageLoanList: StateFlow<List<MortgageLoanProductItemDto>> = _mortgageLoanList.asStateFlow()

    private val _navigateToMortgageLoanList = MutableSharedFlow<Unit>()
    val navigateToMortgageLoanList = _navigateToMortgageLoanList.asSharedFlow()

    private val _rentHouseLoanList = MutableStateFlow<List<RentHouseLoanProductItemDto>>(emptyList())
    val rentHouseLoanList: StateFlow<List<RentHouseLoanProductItemDto>> = _rentHouseLoanList.asStateFlow()

    private val _navigateToRentHouseLoanList = MutableSharedFlow<Unit>()
    val navigateToRentHouseLoanList = _navigateToRentHouseLoanList.asSharedFlow()

    private val _creditLoanList = MutableStateFlow<List<CreditLoanProductItemDto>>(emptyList())
    val creditLoanList: StateFlow<List<CreditLoanProductItemDto>> = _creditLoanList.asStateFlow()

    private val _navigateToCreditLoanList = MutableSharedFlow<Unit>()
    val navigateToCreditLoanList = _navigateToCreditLoanList.asSharedFlow()

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
        _currentViewType.value = ProductViewType.DEPOSIT
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

    // ---- 라벨→서버코드 (예금에서 쓰던 로직 재사용해도 됨) ----
    private fun mapPeriod(label: String?): Int =
        when (label) { "1개월"->1; "3개월"->3; "6개월"->6; "24개월"->24; "36개월"->36; else -> 12 }

    private fun mapFinGrp(label: String) =
        when (label) { "은행"->"bank"; "저축은행"->"savingsBank"; else -> "all" }

    private fun mapRsrvType(label: String?) =
        when (label) { "정액적립식"->"S"; "자유적립식"->"F"; else -> "all" }

    private fun mapIntrType(label: String) =
        when (label) { "단리"->"S"; "복리"->"M"; else -> "all" }

    private fun mapJoinDeny(label: String) =
        when (label) { "서민전용"->"2"; "일부제한"->"3"; else -> "1" }

    // 지역/가입방법(복수선택) 매핑
    private val regionToCode = mapOf(
        "전체" to "all","서울" to "seoul","부산" to "busan","대구" to "daegu","인천" to "incheon",
        "광주" to "gwangju","대전" to "daejeon","울산" to "ulsan","세종" to "sejong","경기" to "gyeonggi",
        "강원" to "gangwon","충북" to "chungbuk","충남" to "chungnam","전북" to "jeonbuk",
        "전남" to "jeonnam","경북" to "kyungbuk","경남" to "kyungnam","제주" to "jeju"
    )
    private fun mapRegions(labels: Set<String>): String =
        if (labels.size == 1 && labels.first() == "전체") "all"
        else labels.mapNotNull { regionToCode[it] }.joinToString(",").ifEmpty { "all" }

    private val joinWayToCode = mapOf(
        "전체" to "all","영업점" to "branch","인터넷" to "internet","스마트폰" to "smartphone",
        "모집인" to "recruiter","전화(텔레뱅킹)" to "telephone","기타" to "others"
    )
    private fun mapJoinWays(labels: Set<String>): String =
        if (labels.size == 1 && labels.first() == "전체") "all"
        else labels.mapNotNull { joinWayToCode[it] }.joinToString(",").ifEmpty { "all" }

    // ---- 호출 함수 (예금과 완전히 동일 흐름) ----
    fun getSavingProductsByLabels(
        savingAmount: Int,
        periodLabel: String?,
        finGrpLabel: String,
        regions: Set<String>,
        rsrvTypeLabel: String?,   // 적금만 추가
        intrTypeLabel: String,
        joinDenyLabel: String,
        joinWayLabels: Set<String>
    ) {
        _currentViewType.value = ProductViewType.SAVING
        viewModelScope.launch {
            runCatching {
                financeRepository.getSavingProducts(
                    savingAmount = savingAmount.coerceAtMost(10_000_000),
                    period      = mapPeriod(periodLabel),
                    finGrpCode  = mapFinGrp(finGrpLabel),
                    region      = mapRegions(regions),
                    rsrvType    = mapRsrvType(rsrvTypeLabel),
                    intrType    = mapIntrType(intrTypeLabel),
                    joinDeny    = mapJoinDeny(joinDenyLabel),
                    joinWay     = mapJoinWays(joinWayLabels)
                )
            }.onSuccess {
                _savingList.value = it
                Log.d("DEBUG_SAVING", "[2] ViewModel SUCCESS. Emitting navigation event now.") // 👈 로그 추가
                _navigateToSavingList.emit(Unit) // 리스트로 이동 신호
            }.onFailure {
                // TODO: 에러 핸들링
            }
        }
    }

    fun getMortgageLoanProductsByLabels(
        mrtgTypeLabel: String?,
        finGrpLabel: String,
        regions: Set<String>,
        rpayTypeLabel: String,
        lendRateTypeLabel: String,
        joinWayLabels: Set<String>
    ) {
        _currentViewType.value = ProductViewType.MORTGAGE_LOAN
        viewModelScope.launch {
            runCatching {
                financeRepository.getMortgageLoanProducts(
                    mrtgType = when(mrtgTypeLabel) { "아파트" -> "A"; "아파트 외" -> "E"; else -> "A" },
                    finGrpCode = mapFinGrp(finGrpLabel),
                    region = mapRegions(regions),
                    rpayType = when(rpayTypeLabel) { "분할상환방식" -> "D"; "만기일시상환방식" -> "S"; else -> "all" },
                    lendRateType = when(lendRateTypeLabel) { "고정금리" -> "F"; "변동금리" -> "C"; else -> "all" },
                    joinWay = mapJoinWays(joinWayLabels)
                )
            }.onSuccess {
                _mortgageLoanList.value = it
                _navigateToMortgageLoanList.emit(Unit)
            }.onFailure {
                Log.e("FinanceViewModel", "주택담보대출 조회 실패", it)
            }
        }
    }

    fun getRentHouseLoanProductsByLabels(
        finGrpLabel: String,
        regions: Set<String>,
        rpayTypeLabel: String,
        lendRateTypeLabel: String,
        joinWayLabels: Set<String>
    ) {
        _currentViewType.value = ProductViewType.RENT_HOUSE_LOAN
        viewModelScope.launch {
            runCatching {
                financeRepository.getRentHouseLoanProducts(
                    finGrpCode = mapFinGrp(finGrpLabel),
                    region = mapRegions(regions),
                    rpayType = when (rpayTypeLabel) {
                        "분할상환방식" -> "D"
                        "만기일시상환방식" -> "S"
                        else -> "all"
                    },
                    lendRateType = when (lendRateTypeLabel) {
                        "고정금리" -> "F"
                        "변동금리" -> "C"
                        else -> "all"
                    },
                    joinWay = mapJoinWays(joinWayLabels)
                )
            }.onSuccess {
                _rentHouseLoanList.value = it
                _navigateToRentHouseLoanList.emit(Unit)
            }.onFailure {
                Log.e("FinanceViewModel", "전세자금대출 조회 실패", it)
            }
        }
    }

    fun getCreditLoanProductsByLabels(
        finGrpLabel: String,
        regions: Set<String>,
        crdtPrdtTypeLabel: String,
        crdtLendRateTypeLabel: String,
        joinWayLabels: Set<String>
    ) {
        _currentViewType.value = ProductViewType.CREDIT_LOAN
        viewModelScope.launch {
            runCatching {
                financeRepository.getCreditLoanProducts(
                    finGrpCode = mapFinGrp(finGrpLabel),
                    region = mapRegions(regions),
                    crdtPrdtType = when (crdtPrdtTypeLabel) {
                        "일반신용대출" -> "1"
                        "마이너스한도대출" -> "2"
                        "장기카드대출" -> "3"
                        else -> "all"
                    },
                    crdtLendRateType = when (crdtLendRateTypeLabel) {
                        "대출금리" -> "A"
                        "기준금리" -> "B"
                        "가산금리" -> "C"
                        "가감조정금리" -> "D"
                        else -> "all"
                    },
                    joinWay = mapJoinWays(joinWayLabels)
                )
            }.onSuccess {
                _creditLoanList.value = it
                _navigateToCreditLoanList.emit(Unit)
            }.onFailure {
                Log.e("FinanceViewModel", "개인신용대출 조회 실패", it)
            }
        }
    }
}
