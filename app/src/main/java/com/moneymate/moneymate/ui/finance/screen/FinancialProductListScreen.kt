package com.moneymate.moneymate.ui.finance.screen

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.finance.response.CreditLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.MortgageLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.RentHouseLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.SavingProductItemDto
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.CreditLoanInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.DepositInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductListItem
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.MortgageLoanInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.ProductViewType
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.RentHouseLoanInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.SavingInfo
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun FinancialProductListScreen(
    modifier: Modifier,
    navController: NavController,
    onNavigateBack: () -> Unit,
    onDepositClick: (DepositProductItemDto) -> Unit,
    onSavingClick: (SavingProductItemDto) -> Unit,
    onMortgageLoanClick: (MortgageLoanProductItemDto) -> Unit,
    onRentHouseLoanClick: (RentHouseLoanProductItemDto) -> Unit,
    onCreditLoanClick: (CreditLoanProductItemDto) -> Unit
) {
    val financeNavGraphEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry(Route.ProductGraph.route)
    }
    val viewModel: FinanceViewModel = hiltViewModel(financeNavGraphEntry)

    val viewType by viewModel.currentViewType.collectAsStateWithLifecycle()
    val deposits by viewModel.depositList.collectAsStateWithLifecycle()
    val savings by viewModel.savingList.collectAsStateWithLifecycle()
    val mortgageLoans by viewModel.mortgageLoanList.collectAsStateWithLifecycle()
    val rentHouseLoans by viewModel.rentHouseLoanList.collectAsStateWithLifecycle()
    val creditLoans by viewModel.creditLoanList.collectAsStateWithLifecycle()

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

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = when (viewType) {
                ProductViewType.DEPOSIT -> "정기 예금 상품 목록"
                ProductViewType.SAVING -> "적금 상품 목록"
                ProductViewType.MORTGAGE_LOAN -> "주택담보대출 상품 목록"
                ProductViewType.RENT_HOUSE_LOAN -> "전세자금대출 상품 목록"
                ProductViewType.CREDIT_LOAN -> "개인신용대출 상품 목록"
            },
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            when (viewType) {
                ProductViewType.DEPOSIT -> {
                    itemsIndexed(
                        items = deposits,
                        key = { index, item -> item.productName.orEmpty() + item.bankName.orEmpty() + index }
                    ) { _, dto ->
                        val ui = DepositInfo(
                            productName = dto.productName.orEmpty(),
                            bankName = dto.bankName.orEmpty(),
                            maxIntrRate = dto.maxIntrRate.orEmpty(),
                            intrType = dto.intrType.orEmpty()
                        )
                        FinancialProductListItem(
                            product = ui,
                            viewType = ProductViewType.DEPOSIT,
                            onClick = { onDepositClick(dto) }
                        )
                    }
                }
                ProductViewType.SAVING -> {
                    itemsIndexed(
                        items = savings,
                        key = { index, item -> item.productName.orEmpty() + item.bankName.orEmpty() + index }
                    ) { _, dto ->
                        val ui = SavingInfo(
                            productName = dto.productName.orEmpty(),
                            bankName = dto.bankName.orEmpty(),
                            maxIntrRate = dto.maxIntrRate.orEmpty(),
                            intrType = dto.intrType.orEmpty()
                        )
                        FinancialProductListItem(
                            product = ui,
                            viewType = ProductViewType.SAVING,
                            onClick = { onSavingClick(dto) }
                        )
                    }
                }
                ProductViewType.MORTGAGE_LOAN -> {
                    itemsIndexed(
                        items = mortgageLoans,
                        key = { index, item -> item.productName.orEmpty() + item.bankName.orEmpty() + index }
                    ) { _, dto ->
                        val ui = MortgageLoanInfo(
                            productName = dto.productName.orEmpty(),
                            bankName = dto.bankName.orEmpty(),
                            type = dto.lendRateType.orEmpty(),
                            minRate = dto.lendRateMin.orEmpty(),
                            maxRate = dto.lendRateMax.orEmpty()
                        )
                        FinancialProductListItem(
                            product = ui,
                            viewType = ProductViewType.MORTGAGE_LOAN,
                            onClick = { onMortgageLoanClick(dto) }
                        )
                    }
                }
                ProductViewType.RENT_HOUSE_LOAN -> {
                    itemsIndexed(
                        items = rentHouseLoans,
                        key = { index, item -> item.productName.orEmpty() + item.bankName.orEmpty() + index }
                    ) { _, dto ->
                        val ui = RentHouseLoanInfo(
                            productName = dto.productName.orEmpty(),
                            bankName = dto.bankName.orEmpty(),
                            type = dto.lendRateType.orEmpty(),
                            minRate = dto.lendRateMin.orEmpty(),
                            maxRate = dto.lendRateMax.orEmpty()
                        )
                        FinancialProductListItem(
                            product = ui,
                            viewType = ProductViewType.RENT_HOUSE_LOAN,
                            onClick = { onRentHouseLoanClick(dto) }
                        )
                    }
                }
                ProductViewType.CREDIT_LOAN -> {
                    itemsIndexed(
                        items = creditLoans,
                        key = { index, item -> item.productName.orEmpty() + item.bankName.orEmpty() + item.crdtLendRateType.orEmpty() + index }
                    ) { _, dto ->
                        val ui = CreditLoanInfo(
                            productName = dto.productName.orEmpty(),
                            bankName = dto.bankName.orEmpty(),
                            type = dto.crdtLendRateType.orEmpty(),
                            rate = dto.crdtGrad9.orEmpty()

                        )
                        FinancialProductListItem(
                            product = ui,
                            viewType = ProductViewType.CREDIT_LOAN,
                            onClick = { onCreditLoanClick(dto) }
                        )
                    }
                }
            }
        }
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun FinancialProductListScreenPreview() {
//    MoneyMateTheme {
//        FinancialProductListScreen(
//            modifier = Modifier,
//            onNavigateBack = {},
//            onDepositClick = {},
//            viewModel = hiltViewModel()
//        )
//    }
//}