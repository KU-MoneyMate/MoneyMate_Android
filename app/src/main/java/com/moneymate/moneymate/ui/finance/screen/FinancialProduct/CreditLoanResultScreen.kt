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
fun CreditLoanResultScreen(
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
            text = "협약금리 外 신용대출상품",
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
                    text = "우리은행",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CB회사명",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "KCB",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            // 저축 금리
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "대출종류",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "마이너스한도대출",
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
                    text = "금리구분",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Text(
                    text = "대출금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "금리",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle
                )
                Column (
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.End
                ){
                    Text(
                        text = "900점 초과 : "+ "4.79" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "801~900점 : "+ "5.11" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "701~800점 : "+ "5.20" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "601~700점 : "+ "6.91" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "501~600점 : "+ "12.00" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "401~500점 : "+ "-" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "301~400점 : "+ "-" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                    Text(
                        text = "300점 이하 : "+ "-" +"%",
                        color = MoneyMateTheme.colors.darkGray,
                        style = ProductTextStyle
                    )
                }

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
                    text = "영업점,인터넷,스마트폰",
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
                    text = "2025-08-19",
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
                    text = "9999-12-31",
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
                    text = "15885000",
                    color = MoneyMateTheme.colors.darkGray,
                    style = ProductTextStyle.copy(textDecoration = TextDecoration.Underline)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreditLoanResultScreenPreview() {
    MoneyMateTheme {
        CreditLoanResultScreen(
            modifier = Modifier,
            onNavigateBack = {}
        )
    }
}