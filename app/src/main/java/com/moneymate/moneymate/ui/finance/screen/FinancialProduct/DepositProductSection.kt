package com.moneymate.moneymate.ui.finance.screen.FinancialProduct

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.finance.component.FinancialProduct.FinancialProductCheckbox
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun DepositProductSection(
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
                modifier = Modifier.padding(top=5.dp),
                text = "저축 금액",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                MoneyMateTextField(
                    Modifier.width(218.7.dp).align(Alignment.CenterVertically), "10000000", {}, {})
                Text(
                    text = "원",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        color = MoneyMateTheme.colors.darkGray
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = "최대 10억원",
                modifier = Modifier
                    .padding(top = 4.dp),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.lightGray
                )
            )
        }
        Row (
            modifier = Modifier
                .padding(top = 37.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "저축 예정 기간",
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
                Row (
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    FinancialProductCheckbox(Modifier, "1개월")
                    FinancialProductCheckbox(Modifier, "3개월")
                    FinancialProductCheckbox(Modifier, "6개월")
                }
                Row (
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ){
                    FinancialProductCheckbox(Modifier, "12개월")
                    FinancialProductCheckbox(Modifier, "24개월")
                    FinancialProductCheckbox(Modifier, "36개월")
                }
            }
        }
        Row (
            modifier = Modifier
                .padding(top = 55.dp)
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
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                FinancialProductCheckbox(Modifier, "전체")
                FinancialProductCheckbox(Modifier, "은행")
                FinancialProductCheckbox(Modifier, "저축은행")
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "이자계산방식",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ){
                FinancialProductCheckbox(Modifier, "전체")
                FinancialProductCheckbox(Modifier, "단리")
                FinancialProductCheckbox(Modifier, "복리")
            }

        }
        Row(
            modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "가입대상",
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
                FinancialProductCheckbox(Modifier, "제한없음")
                FinancialProductCheckbox(Modifier, "서민전용")
                FinancialProductCheckbox(Modifier, "일부제한")
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
fun DepositProductSectionPreview() {
    MoneyMateTheme {
        DepositProductSection(
            modifier = Modifier,
            //onNavigateBack = {}
        )
    }
}