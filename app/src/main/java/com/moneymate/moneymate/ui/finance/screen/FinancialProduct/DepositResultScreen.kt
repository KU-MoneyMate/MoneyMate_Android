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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun DepositResultScreen(
    modifier: Modifier,
    //viewModel: FinanceViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    item : DepositProductItemDto?
) {
    val ProductTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 20.sp
    )
    val scrollState = rememberScrollState()


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
                .padding(top = 36.dp, bottom = 50.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "은행명", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(
                    text = item?.bankName ?: "-",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "저축 금리", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.intrRate?.let { "$it%" } ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "최고 우대금리", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.maxIntrRate?.let { "$it%" } ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "최고한도", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.maxLimit ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "이자 계산 방식", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.intrType ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "만기 후 이자율",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = item?.mtrtInt ?: "-",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    textAlign = TextAlign.End
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "가입 방법", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.joinWay ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "가입 대상", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.joinMember ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "가입 제한", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.joinDeny ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "우대조건",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(
                        text = item?.spclCnd ?: "-",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle,
                        textAlign = TextAlign.End,
                        lineHeight = 28.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "기타 유의사항",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                    Text(
                        text = item?.etcNote ?: "-",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle,
                        textAlign = TextAlign.End,
                        lineHeight = 28.sp
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "공시 시작일", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(text = item?.dclsStrtDay ?: "-", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "공시 종료", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(
                    text = (item?.dclsEndDay?.takeUnless { it == "9999-12-31" } ?: "-"),
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "상담 전화번호", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(
                    text = item?.callNum ?: "-",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}