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
fun MortgageLoanResultScreen(
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
            text = "아파트담보대출(일반)",
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
                    text = "주식회사 케이뱅크",
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
                    text = "대출금리 유형",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "변동금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "최저금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "4.27"+"%",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "최고금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "5.27"+"%",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "전월취급평균금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "4.08"+"%",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "대출 부대비용",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = "1. 인지세 - 해당세액의 50%\n2. 근저당권 설정비 중 고객 부담 항목 \n   - 주택채권 매입비용 등",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "중도상환 수수료",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = "중도상환금액*해약금요율\n(1.40%)*(3년-대출실행 후 경과기간)/3년, \n대출 실행 3년 이후 면제",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "연체이자율",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = "적용금리+3%(최고 15%) \n단, 대출이자가 연 15% 이상일 경우\n 연체이자 연 2%를 가산",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "대출한도",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Text(
                    text = "LTV 최대 70%, \n신규 - 최대 2억원, \n대환 - 최대 10억원",
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
                Text(
                    text = "담보유형",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "아파트",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "대출상환유형",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "분할상환방식",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
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
                    text = "스마트폰",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
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
                    text = "2025-08-20",
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
                    text = "2025-09-20",
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
                    text = "15221000",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MortgageLoanResultScreenPreview() {
    MoneyMateTheme {
        MortgageLoanResultScreen(
            modifier = Modifier,
            onNavigateBack = {}
        )
    }
}