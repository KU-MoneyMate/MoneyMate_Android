package com.moneymate.moneymate.ui.finance.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.CreditLoanInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.DepositInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductListItem
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.MortgageLoanInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.ProductViewType
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.RentHouseLoanInfo
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.SavingInfo
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.SavingProductSection
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun FinancialProductListScreen(
    modifier: Modifier,
    onNavigateBack : ()->Unit
){
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
            text = "정기 예금"+" 상품 목록",
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                fontSize = 18.sp
            )
        )
        Spacer(modifier=Modifier.padding(top = 8.dp))
        val mixedProducts = listOf(
        Pair(
            ProductViewType.DEPOSIT,
            DepositInfo("WON플러스예금", "우리은행", "3.55", "단리")
        ),
        Pair(
            ProductViewType.SAVING,
            SavingInfo("첫 급여 우리 적금", "우리은행", "6.0", "자유적립식")
        ),
        Pair(
            ProductViewType.MORTGAGE_LOAN,
            MortgageLoanInfo("아낌e-보금자리론", "주택금융공사", "4.05", "4.35", "5.0")
        ),
        Pair(
            ProductViewType.RENT_HOUSE_LOAN,
            RentHouseLoanInfo("청년 맞춤형 전세대출", "부산은행", "연 3.8%", "최대 1억원", "3")
        ),
        Pair(
            ProductViewType.CREDIT_LOAN,
            CreditLoanInfo("직장인e든든 신용대출", "국민은행", "고정금리", "5.12")
        )
    )

    LazyColumn {
        items(mixedProducts) { (viewType, product) ->
            FinancialProductListItem(
                product = product,
                viewType = viewType,
                onClick = { /* 상세 화면 이동 */ }
            )
        }
    }

    }





}

@Preview(showBackground = true)
@Composable
fun FinancialProductListScreenPreview() {
    MoneyMateTheme {
        FinancialProductListScreen(
            modifier = Modifier,
            onNavigateBack = {}
        )
    }
}