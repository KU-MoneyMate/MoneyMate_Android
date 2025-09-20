package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductCheckbox
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun RentHouseLoanProductSection(
    modifier: Modifier,

    ){
    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ){
                Row (
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "금융권역",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ){
                FinancialProductCheckbox(Modifier, "전체")
                FinancialProductCheckbox(Modifier, "은행")
                FinancialProductCheckbox(Modifier, "저축은행")
                FinancialProductCheckbox(Modifier, "보험")
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "상환방식",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Column (
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Row (
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    FinancialProductCheckbox(Modifier, "전체")
                    FinancialProductCheckbox(Modifier, "만기일시상환")
                }
                Row (
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    FinancialProductCheckbox(Modifier, "원금분리상환")
                    FinancialProductCheckbox(Modifier, "원리금분리상환")
                }
            }

        }

        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "금리방식",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ){
                FinancialProductCheckbox(Modifier, "전체")
                FinancialProductCheckbox(Modifier, "변동금리")
                FinancialProductCheckbox(Modifier, "고정금리")
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "지역선택",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ){
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "전체")
                    FinancialProductCheckbox(Modifier, "서울")
                    FinancialProductCheckbox(Modifier, "부산")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "대구")
                    FinancialProductCheckbox(Modifier, "인천")
                    FinancialProductCheckbox(Modifier, "광주")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "대전")
                    FinancialProductCheckbox(Modifier, "울산")
                    FinancialProductCheckbox(Modifier, "세종")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "경기")
                    FinancialProductCheckbox(Modifier, "강원")
                    FinancialProductCheckbox(Modifier, "충북")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "충남")
                    FinancialProductCheckbox(Modifier, "전북")
                    FinancialProductCheckbox(Modifier, "전남")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "경북")
                    FinancialProductCheckbox(Modifier, "경남")
                    FinancialProductCheckbox(Modifier, "제주")
                }
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 27.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "가입방법",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "전체")
                    FinancialProductCheckbox(Modifier, "영업점")
                    FinancialProductCheckbox(Modifier, "인터넷")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "스마트폰")
                    FinancialProductCheckbox(Modifier, "모집인")
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FinancialProductCheckbox(Modifier, "전화(텔레뱅킹)")
                    FinancialProductCheckbox(Modifier, "기타")
                }
            }

        }
        Spacer(modifier = Modifier.height(32.dp))
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "조회하기"
        ) {
            // TODO
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RentHouseLoanProductSectionPreview() {
    MoneyMateTheme {
        RentHouseLoanProductSection(
            modifier = Modifier,
            //onNavigateBack = {}
        )
    }
}