package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.manage.screen.OutlinedInputField
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun DeleteAccountScreen(
    modifier : Modifier = Modifier,
    onNavigateBack : () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
                        .clickable { onNavigateBack() }
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "회원 탈퇴",
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
            text = "회원 탈퇴 화면입니다.\n회원 탈퇴를 위해 비밀번호를 입력해 주시기 바랍니다.",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 16.sp
            )
        )


        OutlinedInputField(
            "비밀번호",
            "abcd",
            { },
            "",
            2
        )

        Spacer(modifier = Modifier.weight(1f))
        // 버튼
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "회원 탈퇴하기"
        ) {

        }

    }
}

@Preview(showBackground = true)
@Composable
private fun DeleteAccountScreenPreview() {
    DeleteAccountScreen {

    }
}