package com.moneymate.moneymate.ui.auth.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
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

    val keyboardFocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MoneyMateTheme.colors.white),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoneyMateTextField(
                    modifier = Modifier.size(250.dp, 50.dp),
                    text = verificationCode,
                    onValueChange = { verificationCode = it },
                    placeholder = {
                        Text(
                            text = "아이디를 입력해주세요.",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {keyboardFocusManager.clearFocus()})
                )
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = {
                        viewModel.requestPhoneVerification(viewModel.signupPhone.value)
                        Log.d("SignUpVerificationScreen", "인증번호 재전송 요청: ${viewModel.signupPhone.value}")
                    },
                    contentPadding = PaddingValues( 10.dp, 0.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MoneyMateTheme.colors.deepBlue,
                        contentColor = MoneyMateTheme.colors.white
                    )
                ) {
                    Text(
                        text = "재전송",
                        style = MoneyMateTheme.typography.body_02_SB_12,
                        color = MoneyMateTheme.colors.white
                    )
                }
            }
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
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp),
            ) {
                viewModel.verifyPhoneNumber(
                    phoneNumber = viewModel.signupPhone.value,
                    verificationCode = verificationCode.toInt(),
                    onVerificationSuccess = {
                        viewModel.registerUser {
                            onComplete()
                        }
                    }
                )
            }
        }
    }
}
