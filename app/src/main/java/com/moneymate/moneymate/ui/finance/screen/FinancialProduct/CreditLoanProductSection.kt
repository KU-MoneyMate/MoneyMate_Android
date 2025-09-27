package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductCheckbox
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun CreditLoanProductSection(
    modifier: Modifier,
    onSearchClick: (
        finGrpLabel: String,
        regions: Set<String>,
        crdtPrdtTypeLabel: String,
        crdtLendRateTypeLabel: String,
        joinWayLabels: Set<String>
    ) -> Unit
) {
    val scrollState = rememberScrollState()

    var selectedFinGrp by remember { mutableStateOf("전체") }
    var selectedCrdtPrdtType by remember { mutableStateOf("전체") }
    var selectedLendRateType by remember { mutableStateOf("전체") }
    var selectedRegions by remember { mutableStateOf(setOf("전체")) }
    var selectedJoinWays by remember { mutableStateOf(setOf("전체")) }

    fun toggleMulti(current: Set<String>, item: String, all: String = "전체"): Set<String> =
        if (item == all) setOf(all) else {
            val base = (current - all).toMutableSet()
            if (item in base) base.remove(item) else base.add(item)
            if (base.isEmpty()) setOf(all) else base
        }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.padding(top = 30.dp).fillMaxWidth(),
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
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("전체", "은행", "저축은행").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedFinGrp == label,
                            onToggle = { selectedFinGrp = label }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("여신전문금융", "보험").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedFinGrp == label,
                            onToggle = { selectedFinGrp = label }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 55.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "대출 종류",
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
                    listOf("전체", "일반신용대출").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedLendRateType == label,
                            onToggle = { selectedLendRateType = label }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("마이너스한도대출", "장기카드대출").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedLendRateType == label,
                            onToggle = { selectedLendRateType = label }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 55.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "금리 방식",
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
                    listOf("전체", "대출금리", "기준금리").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedLendRateType == label,
                            onToggle = { selectedLendRateType = label }
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("가산금리", "가감조정금리").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedLendRateType == label,
                            onToggle = { selectedLendRateType = label }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 55.dp).fillMaxWidth(),
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
                listOf(
                    "전체", "서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종",
                    "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주"
                ).chunked(3).forEach { rowItems ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        rowItems.forEach { label ->
                            FinancialProductCheckbox(
                                label = label,
                                isChecked = label in selectedRegions,
                                onToggle = { selectedRegions = toggleMulti(selectedRegions, label) }
                            )
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier.padding(top = 27.dp).fillMaxWidth(),
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
                listOf(
                    listOf("전체", "영업점", "인터넷"),
                    listOf("스마트폰", "모집인"),
                    listOf("전화(텔레뱅킹)", "기타")
                ).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { label ->
                            FinancialProductCheckbox(
                                label = label,
                                isChecked = label in selectedJoinWays,
                                onToggle = {
                                    selectedJoinWays = toggleMulti(selectedJoinWays, label)
                                }
                            )
                        }
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
            onSearchClick(
                selectedFinGrp,
                selectedRegions,
                selectedCrdtPrdtType,
                selectedLendRateType,
                selectedJoinWays
            )
        }
    }
}