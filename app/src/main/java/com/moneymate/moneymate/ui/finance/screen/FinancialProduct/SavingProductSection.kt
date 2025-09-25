package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import android.util.Log
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
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductCheckbox
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun SavingProductSection(
    modifier: Modifier,
    onSearchClick: (
        savingAmount: Int,
        periodLabel: String?,
        finGrpLabel: String,
        regions: Set<String>,
        rsrvTypeLabel: String?,   // ★ 추가
        intrTypeLabel: String,
        joinDenyLabel: String,
        joinWayLabels: Set<String>
    ) -> Unit,
    onNavigateBack: () -> Unit
) {
    var savingAmountText by rememberSaveable { mutableStateOf("1000000") } // 기본 100만원
    val scrollState = rememberScrollState()

    var selectedPeriod by remember { mutableStateOf<String?>(null) }
    var selectedFinGrp by remember { mutableStateOf("전체") }
    var selectedRsrvType by remember { mutableStateOf<String?>("전체") } // ★ 적금 전용
    var selectedIntrType by remember { mutableStateOf("전체") }
    var selectedJoinDeny by remember { mutableStateOf("제한없음") }
    var selectedRegions by remember { mutableStateOf(setOf("전체")) }
    var selectedJoinWays by remember { mutableStateOf(setOf("전체")) }

    fun toggleMulti(current: Set<String>, item: String, all: String = "전체"): Set<String> =
        if (item == all) setOf(all) else {
            val base = (current - all).toMutableSet()
            if (item in base) base.remove(item) else base.add(item)
            if (base.isEmpty()) setOf(all) else base
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(top = 30.dp)
        ) {
            Text(
                "저축 금액",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                MoneyMateTextField(
                    Modifier.width(218.7.dp).align(Alignment.CenterVertically),
                    savingAmountText,
                    { savingAmountText = it.filter(Char::isDigit) },
                    { savingAmountText = "" }
                )
                Text(
                    "원",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = MoneyMateTheme.colors.darkGray
                    )
                )
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                "최대 1000만원",
                color = MoneyMateTheme.colors.lightGray,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium))
            )
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 37.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "저축 예정 기간",
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
                            onToggle = { selectedPeriod = label })
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("12개월", "24개월", "36개월").forEach { label ->
                        FinancialProductCheckbox(
                            label = label,
                            isChecked = selectedPeriod == label,
                            onToggle = { selectedPeriod = label })
                    }
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 55.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "금융권역",
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
                        onToggle = { selectedFinGrp = label })
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 55.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "적립방식",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("전체", "정액적립식", "자유적립식").forEach { label ->
                    FinancialProductCheckbox(
                        label = label,
                        isChecked = selectedRsrvType == label,
                        onToggle = { selectedRsrvType = label })
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 55.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "이자계산방식",
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
                        onToggle = { selectedIntrType = label })
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 55.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "가입대상",
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
                        onToggle = { selectedJoinDeny = label })
                }
            }
        }

        Row(
            Modifier.fillMaxWidth().padding(top = 55.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "지역선택",
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
                    listOf("전체", "서울", "부산"),
                    listOf("대구", "인천", "광주"),
                    listOf("대전", "울산", "세종"),
                    listOf("경기", "강원", "충북"),
                    listOf("충남", "전북", "전남"),
                    listOf("경북", "경남", "제주")
                ).forEach { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        row.forEach { label ->
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
            Modifier.fillMaxWidth().padding(top = 27.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "가입방법",
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
        Log.d("DEBUG_SAVING", "[1] Search Button Clicked in UI") // 👈 로그 추가
        val amount = savingAmountText.filter { it.isDigit() }.toIntOrNull() ?: 0
        onSearchClick(
            amount,
            selectedPeriod,
            selectedFinGrp,
            selectedRegions,
            selectedRsrvType,   // ★ 적금만
            selectedIntrType,
            selectedJoinDeny,
            selectedJoinWays
        )
    }
}

}
