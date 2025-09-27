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
import com.moneymate.moneymate.data.dto.finance.response.SavingProductItemDto
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun SavingResultScreen(
    modifier: Modifier,
    //viewModel: FinanceViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    item: SavingProductItemDto?
) {
    val ProductTextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
        fontSize = 20.sp
    )
    val scrollState = rememberScrollState()

    fun String.formatYmd(): String =
        if (length == 8) "${substring(0,4)}-${substring(4,6)}-${substring(6,8)}" else this


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
            text = item?.productName ?: "-",
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
                Text(text = "은행명", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.bankName ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 저축 금리
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "저축 금리", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.intrRate?.let { "$it%" } ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 최고 우대금리
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "최고 우대금리", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.maxIntrRate?.let { "$it%" } ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 적립 유형
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "적립 유형", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.rsrvType ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
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
                    text = "-",
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
                Text(text = "이자 계산 방식", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.intrType ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 만기 후 이자율
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "만기 후 이자율", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray, modifier = Modifier.padding(end = 16.dp))
                Text(text = item?.mtrtInt ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray, textAlign = TextAlign.End)
            }

            // 가입 방법
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "가입 방법", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.joinWay ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 가입 대상
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "가입 대상", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.joinMember ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 가입 제한
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "가입 제한", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.joinDeny ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }

            // 우대조건
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "우대조건", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray, modifier = Modifier.padding(end = 16.dp))
                Text(text = item?.spclCnd ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray, textAlign = TextAlign.End)
            }

            // 기타 유의사항
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(text = "기타 유의사항", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray, modifier = Modifier.padding(end = 16.dp))
                Text(text = item?.etcNote ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray, textAlign = TextAlign.End)
            }

            // 공시 시작일
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "공시 시작일", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.dclsStrtDay?.let { it.formatYmd() } ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
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
                    text = "-",
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
                Text(text = "상담 전화번호", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
                Text(text = item?.callNum ?: "-", style = ProductTextStyle, color = MoneyMateTheme.colors.darkGray)
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun SavingResultScreenPreview() {
//    MoneyMateTheme {
//        SavingResultScreen(
//            modifier = Modifier,
//            onNavigateBack = {}
//        )
//    }
//}