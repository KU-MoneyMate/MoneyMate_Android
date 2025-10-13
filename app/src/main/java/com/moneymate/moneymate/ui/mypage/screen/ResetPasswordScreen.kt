package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.manage.screen.OutlinedInputField
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun ResetPasswordScreen(
    modifier : Modifier = Modifier,
    onNavigateBack : () -> Unit,
){
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
                modifier = Modifier.weight(3f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "비밀번호 재설정",
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
            text = "새 비밀번호",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 18.sp
            )
        )
        Spacer(Modifier.height(10.dp))
        MoneyMateTextField(
            text = "영문, 숫자 8자리 이상",
            onValueChange = {  },
            placeholder = {
                Text(
                    text = "영문, 숫자 8자리 이상",
                    style = MoneyMateTheme.typography.body_01_M_14
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        Text(
            text = "새 비밀번호 확인",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 18.sp
            )
        )
        Spacer(Modifier.height(10.dp))
        MoneyMateTextField(
            text = "비밀번호 확인",
            onValueChange = {  },
            placeholder = {
                Text(
                    text = "새 비밀번호를 다시 입력해주세요",
                    style = MoneyMateTheme.typography.body_01_M_14
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))
        // 조회하기 버튼
        BottomFullWidthButton(
            modifier = Modifier
                .fillMaxWidth(),
            containerColor = MoneyMateTheme.colors.deepBlue,
            contentColor = MoneyMateTheme.colors.white,
            text = "비밀번호 재설정"
        ) {

        }
    }
}