package com.moneymate.moneymate.ui.manage.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.manage.request.RetireInputRequest
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun RetireInputScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ManageViewModel = hiltViewModel(),
    onNavigateBack: () -> Boolean,
    onNavigateToRetireResult: () -> Unit
){
    val parentEntry = remember { navController.getBackStackEntry(Route.RetireGraph.route) }
    val viewModel: ManageViewModel = hiltViewModel(parentEntry)

    val scrollState = rememberScrollState()

    val retireResult by viewModel.retireResult.collectAsState()

    // 결과가 수신되면 자동으로 결과 화면으로 전환
    LaunchedEffect(retireResult) {
        if (retireResult.isNotEmpty()) {
            onNavigateToRetireResult()
        }
    }

    val age = remember { mutableStateOf("40") }
    val retireAge = remember { mutableStateOf("55") }
    val endAge = remember { mutableStateOf("90") }
    val currentAssets = remember { mutableStateOf("100000000") }
    val annualIncome = remember { mutableStateOf("50000000") }
    val annualExpense = remember { mutableStateOf("30000000") }
    val pensionPerYear = remember { mutableStateOf("24000000") }
    val accounts = remember { mutableStateOf("20000000") }
    val realEstate = remember { mutableStateOf("50000000") }
    val stocks = remember { mutableStateOf("30000000") }
    val assetReturnRate = remember { mutableStateOf("0.05") }
    val incomeGrowthRate = remember { mutableStateOf("0.03") }
    val inflationRate = remember { mutableStateOf("0.02") }
    val pensionStartAge = remember { mutableStateOf("65") }
    val consumptionDropAge = remember { mutableStateOf("65") }
    val consumptionDropRate = remember { mutableStateOf("0.2") }
    val crashCycle = remember { mutableStateOf("10") }
    val crashImpactRate = remember { mutableStateOf("0.15") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_mypage_arrow),
                    contentDescription = "뒤로가기",
                    modifier = Modifier
                        .rotate(180f)
                        .clickable{onNavigateBack()}
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "노후 설계 시뮬레이션",
                    color = MoneyMateTheme.colors.darkGray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        fontSize = 20.sp
                    )
                )
            }
            Box(modifier = Modifier.weight(1f))
        }


        Text(
            text = "노후 자금 설계 시뮬레이션입니다.\n아래 입력되어 있는 기본값들을 수정할 수 있습니다.",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 16.sp
            )
        )
        SectionTitle("나이")
        RowWithTwoInputs("시작 나이", age.value, {age.value = it},"종료 나이", endAge.value, {endAge.value = it})

        SectionTitle("자산")
        OutlinedInputField("현재 순자산", currentAssets.value, { currentAssets.value = it }, "원", 2)
        OutlinedInputField("연간 자산 수익률", assetReturnRate.value, { assetReturnRate.value = it }, "%", 1)

        SectionTitle("소득")
        OutlinedInputField("현재 연간 총수입", annualIncome.value, { annualIncome.value = it }, "원", 2)
        OutlinedInputField("연 소득 증가율", incomeGrowthRate.value, { incomeGrowthRate.value = it }, "%", 1)

        SectionTitle("소비")
        OutlinedInputField("현재 연 소비 금액", annualExpense.value, { annualExpense.value = it }, "원", 2)

        SectionTitle("은퇴")
        OutlinedInputField("은퇴 예상 나이", retireAge.value, { retireAge.value = it }, "세", 1)
        OutlinedInputField("예상 연금 수령 시작 나이", pensionStartAge.value, { pensionStartAge.value = it }, "세", 1)
        OutlinedInputField("예상 연금 수령액", pensionPerYear.value, { pensionPerYear.value = it }, "원", 2)
        OutlinedInputField("소비 감소 시작 나이", consumptionDropAge.value, { consumptionDropAge.value = it }, "세", 1)
        OutlinedInputField("소비 감소율", consumptionDropRate.value, { consumptionDropRate.value = it }, "%", 1)

        SectionTitle("기타")
        OutlinedInputField("연간 인플레이션", inflationRate.value, { inflationRate.value = it }, "%", 1)
        OutlinedInputField("경기침체 주기", crashCycle.value, { crashCycle.value = it }, "년", 1)
        OutlinedInputField("침체시 자산 손실률", crashImpactRate.value, { crashImpactRate.value = it }, "%", 1)

        Spacer(modifier = Modifier.height(32.dp))

        // 조회하기 버튼
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 20.dp, end = 20.dp),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "조회하기"
        ) {
            val request = RetireInputRequest(
                age = age.value.toIntOrNull() ?: 0,
                retireAge = retireAge.value.toIntOrNull() ?: 0,
                currentAssets = currentAssets.value.replace(",", "").toLongOrNull() ?: 0L,
                annualIncome = annualIncome.value.replace(",", "").toLongOrNull() ?: 0L,
                annualExpense = annualExpense.value.replace(",", "").toLongOrNull() ?: 0L,
                pensionPerYear = pensionPerYear.value.replace(",", "").toLongOrNull() ?: 0L,
                accounts = accounts.value.replace(",", "").toLongOrNull() ?: 0L,
                realEstate = realEstate.value.replace(",", "").toLongOrNull() ?: 0L,
                stocks = stocks.value.replace(",", "").toLongOrNull() ?: 0L,
                endAge = endAge.value.toIntOrNull() ?: 0,
                assetReturnRate = assetReturnRate.value.toDoubleOrNull() ?: 0.0,
                incomeGrowthRate = incomeGrowthRate.value.toDoubleOrNull() ?: 0.0,
                inflationRate = inflationRate.value.toDoubleOrNull() ?: 0.0,
                pensionStartAge = pensionStartAge.value.toIntOrNull() ?: 0,
                consumptionDropAge = consumptionDropAge.value.toIntOrNull() ?: 0,
                consumptionDropRate = consumptionDropRate.value.toDoubleOrNull() ?: 0.0,
                crashCycle = crashCycle.value.toIntOrNull() ?: 0,
                crashImpactRate = crashImpactRate.value.toDoubleOrNull() ?: 0.0
            )

            viewModel.postRetirementSimulation(request)

        }
        Spacer(modifier=Modifier.height(28.dp))
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            fontSize = 18.sp
        ),
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
    )
}

@Composable
fun OutlinedInputField(label: String, value: String, onValueChange: (String) -> Unit, unit: String? = null, type: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.width(if (type == 2) 141.dp else 66.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            )
        )

        if (unit != null) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = unit,
                modifier = Modifier.padding(top = 20.dp),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
        }
    }
}

@Composable
fun RowWithTwoInputs(
    label1: String,
    value1: String,
    onValue1Change: (String) -> Unit,
    label2: String,
    value2: String,
    onValue2Change: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        OutlinedTextField(
            value = value1,
            onValueChange = onValue1Change,
            label = { Text(label1) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value2,
            onValueChange = onValue2Change,
            label = { Text(label2) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "", modifier = Modifier.padding(top = 20.dp))
    }
}
