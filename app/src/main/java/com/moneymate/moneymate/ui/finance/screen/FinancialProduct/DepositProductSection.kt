package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductCheckbox
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun DepositProductSection(
    modifier: Modifier,
    onSearchClick: (
        savingAmount: Int,
        periodLabel: String?,
        finGrpLabel: String,
        regions: Set<String>,
        intrTypeLabel: String,
        joinDenyLabel: String,
        joinWayLabels: Set<String>
    ) -> Unit,
    onNavigateBack: () -> Unit
){
    var savingAmountText by rememberSaveable { mutableStateOf("10000000") }

    val scrollState = rememberScrollState()

    var selectedPeriod by remember { mutableStateOf<String?>(null) }
    var selectedFinGrp by remember { mutableStateOf("전체") }
    var selectedIntrType by remember { mutableStateOf("전체") }
    var selectedJoinDeny by remember { mutableStateOf("제한없음") }
    var selectedRegions by remember { mutableStateOf(setOf("전체")) }
    var selectedJoinWays by remember { mutableStateOf(setOf("전체")) }

    fun toggleMulti(current: Set<String>, item: String, all: String = "전체"): Set<String> {
        return if (item == all) setOf(all) else {
            val base = (current - all).toMutableSet()
            if (item in base) base.remove(item) else base.add(item)
            if (base.isEmpty()) setOf(all) else base
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "저축 금액",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                MoneyMateTextField(
                    Modifier.width(218.7.dp).align(Alignment.CenterVertically),
                    savingAmountText,
                    { newText ->
                        // 숫자만 허용 (원하는 정책대로 수정 가능)
                        savingAmountText = newText.filter { it.isDigit() }
                    },
                    {
                        // 두 번째 람다는 네 컴포넌트 시그니처에 맞춰 유지 (예: clear 등)
                        savingAmountText = ""
                    }
                )
                Text(
                    text = "원",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = MoneyMateTheme.colors.darkGray
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "최대 10억원",
                modifier = Modifier.padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.lightGray
                )
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 37.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "저축 예정 기간",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("1개월", "3개월", "6개월").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedPeriod == label,
                            onToggle = { selectedPeriod = label },
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("12개월", "24개월", "36개월").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedPeriod == label,
                            onToggle = { selectedPeriod = label }
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "금융권역",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                listOf("전체", "은행", "저축은행").forEach { label ->
                    FinancialProductCheckbox(
                        label = label,
                        isChecked = selectedFinGrp == label,
                        onToggle = { selectedFinGrp = label }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "이자계산방식",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                listOf("전체", "단리", "복리").forEach { label ->
                    FinancialProductCheckbox(
                        label = label,
                        isChecked = selectedIntrType == label,
                        onToggle = { selectedIntrType = label }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "가입대상",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("제한없음", "서민전용", "일부제한").forEach { label ->
                    FinancialProductCheckbox(
                        label = label,
                        isChecked = selectedJoinDeny == label,
                        onToggle = { selectedJoinDeny = label }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "지역선택",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("전체", "서울", "부산").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedRegions,
                            onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("대구", "인천", "광주").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedRegions,
                            onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("대전", "울산", "세종").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedRegions,
                            onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("경기", "강원", "충북").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedRegions,
                            onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("충남", "전북", "전남").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedRegions,
                            onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("경북", "경남", "제주").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedRegions,
                            onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 27.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "가입방법",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("전체", "영업점", "인터넷").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedJoinWays,
                            onToggle = { selectedJoinWays = toggleMulti(selectedJoinWays, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("스마트폰", "모집인").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedJoinWays,
                            onToggle = { selectedJoinWays = toggleMulti(selectedJoinWays, label) }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    listOf("전화(텔레뱅킹)", "기타").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = label in selectedJoinWays,
                            onToggle = { selectedJoinWays = toggleMulti(selectedJoinWays, label) }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "조회하기"
        ) {
            val amount = savingAmountText.filter { it.isDigit() }.toIntOrNull() ?: 0
            onSearchClick(
                amount,
                selectedPeriod,
                selectedFinGrp,
                selectedRegions,
                selectedIntrType,
                selectedJoinDeny,
                selectedJoinWays
            )
        }
    }
}
