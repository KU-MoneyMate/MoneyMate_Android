package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.finance.response.CreditLoanProductItemDto
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun CreditLoanResultScreen(
    modifier: Modifier,
    onNavigateBack: () -> Unit,
    item: CreditLoanProductItemDto?
) {
    val ProductTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 20.sp
    )
    val scrollState = rememberScrollState()

    fun String.formatYmd(): String =
        if (length == 8) "${substring(0, 4)}-${substring(4, 6)}-${substring(6, 8)}" else this

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
            text = item?.productName ?: "-",
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 24.sp
            ),
            modifier = Modifier
                .padding(top = 36.dp, bottom = 24.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            InfoRow(label = "은행명", value = item?.bankName, isPhoneNum = false)
            InfoRow(label = "CB회사명", value = item?.cbName, isPhoneNum = false)
            InfoRow(label = "대출 종류", value = item?.crdtPrdtType, isPhoneNum = false)
            InfoRow(label = "금리 구분", value = item?.crdtLendRateType, isPhoneNum = false)

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "신용등급별 금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(text = "901점 이상: ${item?.crdtGrad9 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "801~900점: ${item?.crdtGrad98 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "701~800점: ${item?.crdtGrad87 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "601~700점: ${item?.crdtGrad76 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "501~600점: ${item?.crdtGrad65 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "401~500점: ${item?.crdtGrad54 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "301~400점: ${item?.crdtGrad43 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                    Text(text = "300점 이하: ${item?.crdtGrad3 ?: "-"}%", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                }
            }

            InfoRow(label = "가입 방법", value = item?.joinWay, isPhoneNum = false)
            InfoRow(label = "공시 시작일", value = item?.dclsStrtDay?.formatYmd(), isPhoneNum = false)
            InfoRow(
                label = "공시 종료일",
                value = item?.dclsEndDay?.takeUnless { it.isBlank() }?.formatYmd(),
                isPhoneNum = false
            )
            InfoRow(label = "상담 전화번호", value = item?.callNum, isPhoneNum = true)
        }
    }
}
