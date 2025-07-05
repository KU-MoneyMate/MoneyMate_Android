package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun SignUpVerificationScreen(
    modifier: Modifier,
    viewModel: AuthViewModel,
    onComplete: () -> Unit
) {
    var verificationCode by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MoneyMateTheme.colors.white),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(75.dp))

            Text(
                text = "인증번호를 입력해주세요",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold))
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            MoneyMateTextField(
                text = verificationCode,
                onValueChange = { verificationCode = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "인증번호",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BottomFullWidthButton(
                containerColor = MoneyMateTheme.colors.deepBlue,
                contentColor = Color.White,
                text = "회원가입 완료하기",
                modifier = Modifier
                    .width(320.dp)
                    .padding(bottom = 30.dp)
            ) {
                viewModel.registerUser {
                    onComplete()
                }
            }
        }
    }
}
