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
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun DepositProductResultScreen(
    modifier: Modifier,
    //viewModel: FinanceViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
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

        // 상품명
        Text(
            text = "e-The프리미엄 회전정기예금",
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(top = 36.dp, bottom = 50.dp)
                .align(alignment = Alignment.CenterHorizontally)

        )

        Column (
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            // 은행명
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "은행명", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
                Text(
                    text = "예가람저축은행",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }

            // 저축 금리
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "저축 금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "1.8%",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 최고 우대금리
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "최고 우대금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "3.30%",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 최고한도
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "최고한도",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "10억원",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 이자 계산 방식
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "이자 계산 방식",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(text = "복리", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }

            // 만기 후 이자율
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
                    text = "* 만기후 1개월 이내 : 마지막 회전주기의 약정이율과 만기일에 공시된 이 상품의 이율 중 낮은 이율\n* 만기후 1개월 초과 : 보통예금금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    textAlign = TextAlign.End
                )

            }

            // 가입 방법
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "가입 방법",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "인터넷, 스마트폰",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 가입 대상
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "가입 대상",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "제한 없음",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 가입 제한
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "가입 제한",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(text = "없음", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }

            // 우대조건
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "우대조건",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(text = "없음", color = MoneyMateTheme.colors.darkGray, style = ProductTextStyle)
            }

            // 기타 유의사항
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
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = "인터넷뱅킹, 스마트폰\n* 계약기간 36개월(12개월 회전주기 변동금리 상품)",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle,
                        textAlign = TextAlign.End,
                        lineHeight = 28.sp
                    )
                }
            }

            // 공시 시작일
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "공시 시작일",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "2025-06-13",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 공시 종료
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "공시 종료",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "2027-08-21",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 상담 전화번호
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "상담 전화번호",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "1877-7788",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavingProductResultScreenPreview() {
    MoneyMateTheme {
        DepositProductResultScreen(
            modifier = Modifier,
            onNavigateBack = {}
        )
    }
}