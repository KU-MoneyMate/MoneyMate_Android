package com.moneymate.moneymate.ui.finance.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.SavingProductSection
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun FinancialProductScreen(
    modifier: Modifier,
    //viewModel: FinanceViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
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


        //상품 종류 선택
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "상품 종류",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                ),
            )
            Spacer(Modifier.width(17.dp))

            //드롭다운
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                val shape = RoundedCornerShape(10.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(41.dp)
                        .clip(shape)
                        .border(
                            width = 1.dp,
                            color = MoneyMateTheme.colors.darkGray,
                            shape = shape
                        )
                        .clickable {  },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "선택해주세요.",
                        style = TextStyle(
                            color = MoneyMateTheme.colors.darkGray,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            fontSize = 18.sp
                        ),
                        maxLines = 1,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 17.dp)
                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_mypage_arrow),
                        contentDescription = "드롭다운 아이콘",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 13.dp)
                            .rotate(90F),
                    )
                }
            }
        }

        SavingProductSection(Modifier)
    }
}



@Preview(showBackground = true)
@Composable
fun FinancialProductScreenPreview() {
    MoneyMateTheme {
        FinancialProductScreen(
            modifier = Modifier,

            onNavigateBack = {}
        )
    }
}