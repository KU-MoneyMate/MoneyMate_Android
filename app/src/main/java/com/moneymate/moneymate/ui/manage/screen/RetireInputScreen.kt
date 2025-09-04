package com.moneymate.moneymate.ui.manage.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.moneymate.moneymate.util.toDecimalFormat

@Composable
fun RetireInputScreen(
    modifier: Modifier = Modifier,
    viewModel: ManageViewModel = hiltViewModel(),
    onNavigateBack: () -> Boolean,
    onNavigateToRetireResult: () -> Unit
){

    val scrollState = rememberScrollState()

    val totalAsset = viewModel.totalAsset.collectAsStateWithLifecycle()
    val retireResult by viewModel.retireResult.collectAsStateWithLifecycle()

    BackHandler {
        onNavigateBack()
    }

    val age = viewModel.age.collectAsStateWithLifecycle().value
    val retireAge = viewModel.retireAge.collectAsStateWithLifecycle().value
    val endAge = viewModel.endAge.collectAsStateWithLifecycle().value
    val annualIncome = viewModel.annualIncome.collectAsStateWithLifecycle().value
    val annualExpense = viewModel.annualExpense.collectAsStateWithLifecycle().value
    val pensionPerYear = viewModel.pensionPerYear.collectAsStateWithLifecycle().value
    val accounts = viewModel.accounts.collectAsStateWithLifecycle().value
    val realEstate = viewModel.realEstate.collectAsStateWithLifecycle().value
    val stocks = viewModel.stocks.collectAsStateWithLifecycle().value
    val assetReturnRate = viewModel.assetReturnRate.collectAsStateWithLifecycle().value
    val incomeGrowthRate = viewModel.incomeGrowthRate.collectAsStateWithLifecycle().value
    val inflationRate = viewModel.inflationRate.collectAsStateWithLifecycle().value
    val pensionStartAge = viewModel.pensionStartAge.collectAsStateWithLifecycle().value
    val consumptionDropAge = viewModel.consumptionDropAge.collectAsStateWithLifecycle().value
    val consumptionDropRate = viewModel.consumptionDropRate.collectAsStateWithLifecycle().value
    val crashCycle = viewModel.crashCycle.collectAsStateWithLifecycle().value
    val crashImpactRate = viewModel.crashImpactRate.collectAsStateWithLifecycle().value

    //총자산조회 결과 조회, 반영
    LaunchedEffect(Unit){
        viewModel.getTotalAsset()
    }
    

    Column(
        modifier = modifier
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
        RowWithTwoInputs("시작 나이", age, { viewModel.updateAge(it) },"종료 나이", endAge, { viewModel.updateEndAge(it) })

        SectionTitle("자산")
        OutlinedInputField("현재 순자산", totalAsset.value?.toString() ?: "0", { viewModel.updateTotalAsset(it) }, "원", 2)
        OutlinedInputField("연간 자산 수익률", assetReturnRate, { viewModel.updateAssetReturnRate(it) }, "%", 1)

        SectionTitle("소득")
        OutlinedInputField("현재 연간 총수입", annualIncome, { viewModel.updateAnnualIncome(it) }, "원", 2)
        OutlinedInputField("연 소득 증가율", incomeGrowthRate, { viewModel.updateIncomeGrowthRate(it) }, "%", 1)

        SectionTitle("소비")
        OutlinedInputField("현재 연 소비 금액", annualExpense, { viewModel.updateAnnualExpense(it) }, "원", 2)

        SectionTitle("은퇴")
        OutlinedInputField("은퇴 예상 나이", retireAge, { viewModel.updateRetireAge(it) }, "세", 1)
        OutlinedInputField("예상 연금 수령 시작 나이", pensionStartAge, { viewModel.updatePensionStartAge(it) }, "세", 1)
        OutlinedInputField("예상 연금 수령액", pensionPerYear, { viewModel.updatePensionPerYear(it) }, "원", 2)
        OutlinedInputField("소비 감소 시작 나이", consumptionDropAge, { viewModel.updateConsumptionDropAge(it) }, "세", 1)
        OutlinedInputField("소비 감소율", consumptionDropRate, { viewModel.updateConsumptionDropRate(it) }, "%", 3)

        SectionTitle("기타")
        OutlinedInputField("연간 인플레이션", inflationRate, { viewModel.updateInflationRate(it) }, "%", 1)
        OutlinedInputField("경기침체 주기", crashCycle, { viewModel.updateCrashCycle(it) }, "년", 1)
        OutlinedInputField("침체시 자산 손실률", crashImpactRate, { viewModel.updateCrashImpactRate(it) }, "%", 3)

        Spacer(modifier = Modifier.height(32.dp))

        // 조회하기 버튼
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "조회하기"
        ) {
            val request = RetireInputRequest(
                age = age.toIntOrNull() ?: 0,
                retireAge = retireAge.toIntOrNull() ?: 0,
                currentAssets = totalAsset.value ?: 0L,
                annualIncome = annualIncome.replace(",", "").toLongOrNull() ?: 0L,
                annualExpense = annualExpense.replace(",", "").toLongOrNull() ?: 0L,
                pensionPerYear = pensionPerYear.replace(",", "").toLongOrNull() ?: 0L,
                accounts = accounts.replace(",", "").toLongOrNull() ?: 0L,
                realEstate = realEstate.replace(",", "").toLongOrNull() ?: 0L,
                stocks = stocks.replace(",", "").toLongOrNull() ?: 0L,
                endAge = endAge.toIntOrNull() ?: 0,
                assetReturnRate = assetReturnRate.toDoubleOrNull()?.div(100) ?: 0.0,
                incomeGrowthRate = incomeGrowthRate.toDoubleOrNull()?.div(100) ?: 0.0,
                inflationRate = inflationRate.toDoubleOrNull()?.div(100) ?: 0.0,
                pensionStartAge = pensionStartAge.toIntOrNull() ?: 0,
                consumptionDropAge = consumptionDropAge.toIntOrNull() ?: 0,
                consumptionDropRate = consumptionDropRate.toDoubleOrNull()?.div(100) ?: 0.0,
                crashCycle = crashCycle.toIntOrNull() ?: 0,
                crashImpactRate = crashImpactRate.toDoubleOrNull()?.div(100) ?: 0.0
            )

            viewModel.postRetirementSimulation(request)
            onNavigateToRetireResult()

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
fun OutlinedInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    unit: String? = null,
    type: Int
) {
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
        if (type == 3) {
            Text(
                text = "-",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.width(5.dp))
        }

        MoneyMateTextField(
            text = if (type == 2) value.toLongOrNull()?.toDecimalFormat() ?: value else value,
            onValueChange = { newValue ->
                if (type == 2) {
                    // 숫자로 포매팅
                    val numericValue = newValue.replace(Regex("[^0-9]"), "")
                    onValueChange(numericValue)
                } else {
                    onValueChange(newValue)
                }
            },
            modifier = Modifier.width(if (type == 2) 141.dp else 66.dp).height(56.dp),
            placeholder = {
                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )

        if (unit != null) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = unit,
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
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .weight(1f)
        ){
            Text(
                text = label1,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.width(7.dp))
            MoneyMateTextField(
                text = value1,
                onValueChange = { newValue ->
                    // 숫자로 포매팅
                    val numericValue = newValue.replace(Regex("[^0-9]"), "")
                    onValue1Change(numericValue)
                },
                modifier = Modifier
                    .width(66.dp)
                    .height(56.dp),
                placeholder = {
                    Text(
                        text = label1,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .weight(1f)
        ){
            Text(
                text = label2,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.width(7.dp))
            MoneyMateTextField(
                text = value2,
                onValueChange = { newValue ->
                    // 숫자로 포매팅
                    val numericValue = newValue.replace(Regex("[^0-9]"), "")
                    onValue2Change(numericValue)
                },
                modifier = Modifier
                    .width(66.dp)
                    .height(56.dp),
                placeholder = {
                    Text(
                        text = label2,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}
