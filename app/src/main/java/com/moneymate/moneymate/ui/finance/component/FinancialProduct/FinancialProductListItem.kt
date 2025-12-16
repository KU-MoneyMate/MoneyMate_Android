package com.moneymate.moneymate.ui.finance.component.FinancialProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun FinancialProductListItem(
    product: FinancialProductInfo,
    viewType: ProductViewType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //val cleanedProductName = product.productName.replace("\n", " ")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Spacer(Modifier.height(22.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                //상품명
                Text(
                    text = product.productName,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 20.sp,
                    color = MoneyMateTheme.colors.darkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "은행명: ${product.bankName}",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )

                when (viewType) {
                    ProductViewType.DEPOSIT -> {
                        val item = product as? DepositInfo ?: return@Column
                        DetailText(text = "최고 우대금리: ${item.maxIntrRate}%")
                        DetailText(text = "이자 계산방식: ${item.intrType}")
                    }
                    ProductViewType.SAVING -> {
                        val item = product as? SavingInfo ?: return@Column
                        DetailText(text = "최고 우대금리: ${item.maxIntrRate}%")
                        DetailText(text = "이자 계산방식: ${item.intrType}")
                    }
                    ProductViewType.MORTGAGE_LOAN -> {
                        val item = product as? MortgageLoanInfo ?: return@Column
                        DetailText(text = "대출금리유형: ${item.type}%")
                        DetailText(text = "최저금리: ${item.minRate}%")
                        DetailText(text = "최고금리: ${item.maxRate}%")
                    }
                    ProductViewType.RENT_HOUSE_LOAN -> {
                        val item = product as? RentHouseLoanInfo ?: return@Column
                        DetailText(text = "대출금리유형: ${item.type}")
                        DetailText(text = "최저금리: ${item.minRate}%")
                        DetailText(text = "최고금리: ${item.maxRate}%")
                    }
                    ProductViewType.CREDIT_LOAN -> {
                        val item = product as? CreditLoanInfo ?: return@Column
                        DetailText(text = "대출종류: ${item.type}")
                        DetailText(text = "금리(%): ${item.rate}%")
                    }
                }
            }

            Icon(
                painter = painterResource(R.drawable.ic_mypage_arrow),
                contentDescription = "상세보기",
                tint = MoneyMateTheme.colors.darkGray,
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(Modifier.height(22.dp))
        Spacer(Modifier.fillMaxWidth().height(1.dp).background(MoneyMateTheme.colors.darkGray))
    }
}

/** 상세 정보 Text 스타일을 통일하기 위한 Helper Composable */
@Composable
private fun DetailText(text: String) {
    Text(
        text = text,
        fontSize = 18.sp,
        color = MoneyMateTheme.colors.darkGray,
        fontFamily = FontFamily(Font(R.font.pretendard_medium))
    )
}

enum class ProductViewType {
    /** /deposit */
    DEPOSIT,

    /** /saving */
    SAVING,

    /** /mortgage-loan */
    MORTGAGE_LOAN,

    /** /rent-house-loan */
    RENT_HOUSE_LOAN,

    /** /credit-loan */
    CREDIT_LOAN
}

/**
 * 2. 모든 금융상품이 공통적으로 가질 정보를 정의하는 Sealed Interface
 */
sealed interface FinancialProductInfo {
    val productName: String
    val bankName: String
}

// --- 각 상품별 데이터 클래스 정의 ---

data class DepositInfo( // 정기예금
    override val productName: String,
    override val bankName: String,
    val maxIntrRate: String,
    val intrType: String,
) : FinancialProductInfo

data class SavingInfo( // 적금
    override val productName: String,
    override val bankName: String,
    val maxIntrRate: String,
    val intrType: String,
) : FinancialProductInfo

data class MortgageLoanInfo( // 주택담보대출
    override val productName: String,
    override val bankName: String,
    val type: String,
    val minRate: String,
    val maxRate: String,
) : FinancialProductInfo

data class RentHouseLoanInfo( // 전세자금대출
    override val productName: String,
    override val bankName: String,
    val type: String,
    val minRate: String,
    val maxRate: String,
) : FinancialProductInfo

data class CreditLoanInfo( // 개인신용대출
    override val productName: String,
    override val bankName: String,
    val type: String,
    val rate: String,
) : FinancialProductInfo

@Preview(showBackground = true)
@Composable
fun FinalListPreview() {
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

    MaterialTheme {
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