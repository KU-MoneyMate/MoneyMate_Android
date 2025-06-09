package com.moneymate.moneymate.ui.manage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun RetireInputScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel(),
    onNavigateBack: () -> Boolean
){
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
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
                        .clickable{onNavigateBack()}
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "노후 설계 시뮬레이션",
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
            text = "노후 자금 설계 시뮬레이션입니다.\n아래 입력되어 있는 기본값들을 수정할 수 있습니다.",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 16.sp
            )
        )
        SectionTitle("나이")
        RowWithTwoInputs("시작 나이", "30", "종료 나이", "90")

        SectionTitle("자산")
        OutlinedInputField(label = "현재 순자산", value = "56,000,000", unit = "원", type = 2)
        OutlinedInputField(label = "연간 자산 수익률", value = "7", unit = "%", type = 1)

        SectionTitle("소득")
        OutlinedInputField(label = "현재 연간 총수입", value = "0", unit = "원", type = 2)
        OutlinedInputField(label = "연 소득 증가율", value = "4", unit = "%", type = 1)

        SectionTitle("소비")
        OutlinedInputField(label = "현재 연 소비 금액", value = "0", unit = "원", type = 2)

        SectionTitle("은퇴")
        OutlinedInputField(label = "은퇴 예상 나이", value = "55", unit = "세",type = 1)
        OutlinedInputField(label = "예상 연금 수령 시작 나이", value = "60", unit = "세",type = 1)
        OutlinedInputField(label = "예상 연금 수령액", value = "0", unit = "원", type = 2)
        OutlinedInputField(label = "소비 감소 시작 나이", value = "70", unit = "세", type = 1)
        OutlinedInputField(label = "소비 감소율", value = "-20", unit = "%", type = 1)

        SectionTitle("기타")
        OutlinedInputField(label = "연간 인플레이션", value = "2", unit = "%", type = 1)
        OutlinedInputField(label = "경기침체 주기", value = "6", unit = "년", type = 1)
        OutlinedInputField(label = "침체시 자산 손실률", value = "-10", unit = "%", type = 1)

        Spacer(modifier = Modifier.height(32.dp))

        // 조회하기 버튼
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "조회하기"
        ) {
            // TODO
        }
        Spacer(modifier=Modifier.height(63.dp))
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            fontSize = 18.sp
        ),
        modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
    )
}

@Composable
fun OutlinedInputField(label: String, value: String, unit: String? = null, type:Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 18.sp
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        if (type == 2){
            OutlinedTextField(
                value = value,
                onValueChange = {},
                modifier = Modifier.width(141.dp),
                textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.pretendard_regular)))
            )
        }
        else if (type == 1){
            OutlinedTextField(
                value = value,
                onValueChange = {},
                modifier = Modifier.width(66.dp),
                textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.pretendard_regular)))
            )
        }

        if (unit != null) {
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = unit, modifier = Modifier.padding(top = 20.dp),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 18.sp
            ))
        }
    }
}

@Composable
fun RowWithTwoInputs(
    label1: String,
    value1: String,
    label2: String,
    value2: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        OutlinedTextField(
            value = value1,
            onValueChange = {},
            label = { Text(label1) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedTextField(
            value = value2,
            onValueChange = {},
            label = { Text(label2) },
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "", modifier = Modifier.padding(top = 20.dp))
    }
}
