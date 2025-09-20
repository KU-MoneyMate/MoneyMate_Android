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
fun RentHouseLoanResultScreen(
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
                    text = "4.16"+"%",
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
                    text = "5.26"+"%",
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
                    text = "3.79"+"%",
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
                    text = "- 인지세 : 해당세액의 50%(대출금액 5천만원 이하시 없음)\n- 국민주택채권 매입 : 대출금액 × 120% × 1% × 채권할인율",
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
                    text = "- 고정금리 : 중도상환대출금×0.74%×잔존기간÷대출기간\n- 변동금리 : 중도상환대출금×0.74%×잔존기간÷대출기간",
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
                    text = "- 적용금리+ 3%\n(가계대출 최고연체이자율 : 12%)",
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
                    text = "대출한도",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "5억원",
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
                    text = "만기일시상환방식",
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
                    text = "인터넷, 스마트폰",
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
fun RentHouseLoanResultScreenPreview() {
    MoneyMateTheme {
        RentHouseLoanResultScreen(
            modifier = Modifier,
            onNavigateBack = {}
        )
    }
}