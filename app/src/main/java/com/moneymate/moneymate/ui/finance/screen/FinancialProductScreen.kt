package com.moneymate.moneymate.ui.finance.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.FinanceViewModel
//import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.CreditLoanProductSection
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.DepositProductSection
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.SavingProductSection
import com.moneymate.moneymate.ui.navigation.Route
//import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.MortgageLoanProductSection
//import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.RentHouseLoanProductSection
//import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.SavingProductSection
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedGetBackStackEntry")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinancialProductScreen(
    modifier: Modifier,
    navController: NavController,
    onNavigateBack: () -> Unit,
    onNavigateToDepositList: () -> Unit,
    onNavigateToSavingList: () -> Unit,
) {

    val financeNavGraphEntry = remember(navController) {
        navController.getBackStackEntry(Route.ProductGraph.route)
    }
    val viewModel: FinanceViewModel = hiltViewModel(financeNavGraphEntry)

    val lifecycleOwner = LocalLifecycleOwner.current

    // ✅ key에 lifecycleOwner를 포함시키고, 블록 안에선 이미 캡처된 값을 사용
    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch { viewModel.navigateToDepositList.collect { onNavigateToDepositList() } }
            launch {
                viewModel.navigateToSavingList.collect {
                    Log.d("DEBUG_SAVING", "[3] Navigation event RECEIVED in UI. Navigating now.") // 👈 로그 추가
                    onNavigateToSavingList()
                } }
        }
    }

    // 1. 드롭다운 메뉴에 표시할 항목 리스트
    val items = listOf("정기 예금", "적금", "주택담보대출", "전세자금대출", "개인신용대출")

    // 2. 메뉴의 확장 상태를 저장하는 변수 (열렸는지, 닫혔는지)
    var expanded by remember { mutableStateOf(false) }

    // 3. 현재 선택된 항목의 텍스트를 저장하는 변수
    var selectedText by remember { mutableStateOf("선택해주세요.") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
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
                        .clickable { onNavigateBack() }
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "은행 상품 정보",
                    color = MoneyMateTheme.colors.darkGray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        fontSize = 20.sp
                    )
                )
            }
            Box(modifier = Modifier.weight(1f))
        }


        //상품 종류 선택
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "상품 종류",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                ),
            )
            Spacer(Modifier.width(17.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded //확장 상태를 반전시켜 메뉴를 열고 닫음
                }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor()
                        .padding(start = 15.dp),
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(10.dp),
                    textStyle = TextStyle(
                        color = MoneyMateTheme.colors.darkGray,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        fontSize = 18.sp,
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MoneyMateTheme.colors.darkGray,
                        unfocusedBorderColor = MoneyMateTheme.colors.darkGray,
                        cursorColor = MoneyMateTheme.colors.darkGray
                    ),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },

                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedText = item
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        when (selectedText) {
            "정기 예금" -> DepositProductSection(
                modifier = modifier,
                // [중요] 조회 버튼 클릭 시 현재 선택값을 받아 API 호출 + 리스트로 이동
                onSearchClick = { savingAmount, periodLabel, finGrpLabel, regions, intrTypeLabel, joinDenyLabel, joinWayLabels ->
                    viewModel.getDepositProductsByLabels(
                        savingAmount = savingAmount,
                        periodLabel = periodLabel,
                        finGrpLabel = finGrpLabel,
                        regions = regions,
                        intrTypeLabel = intrTypeLabel,
                        joinDenyLabel = joinDenyLabel,
                        joinWayLabels = joinWayLabels
                    )
                },
                onNavigateBack = onNavigateBack
            )
            "적금" -> SavingProductSection(
                Modifier,
                onSearchClick = { savingAmount, periodLabel, finGrpLabel, regions, rsrvTypeLabel, intrTypeLabel, joinDenyLabel, joinWayLabels ->
                    viewModel.getSavingProductsByLabels(
                        savingAmount = savingAmount,
                        periodLabel = periodLabel,
                        finGrpLabel = finGrpLabel,
                        regions = regions,
                        rsrvTypeLabel = rsrvTypeLabel,
                        intrTypeLabel = intrTypeLabel,
                        joinDenyLabel = joinDenyLabel,
                        joinWayLabels = joinWayLabels
                    )
                },
                onNavigateBack = onNavigateBack
            )
//            "개인신용대출" -> CreditLoanProductSection(Modifier)
//            "주택담보대출" -> MortgageLoanProductSection(Modifier)
//            "전세자금대출" -> RentHouseLoanProductSection(Modifier)
            else -> {  }
        }
    }
}


