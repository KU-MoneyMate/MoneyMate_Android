package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.moneymate.moneymate.util.auth.isValidPassword
import kotlinx.coroutines.launch

@Composable
fun SignUpPWScreen(
    modifier: Modifier,
    viewModel: AuthViewModel,
    onNext: () -> Unit
) {
    var pw by rememberSaveable { mutableStateOf("") }
    var confirmPw by rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                    text = "비밀번호를 입력해주세요",
                    color = MoneyMateTheme.colors.black,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold))
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "영문, 숫자 포함 8자 이상",
                    color = MoneyMateTheme.colors.neutral500,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular))
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                MoneyMateTextField(
                    text = pw,
                    onValueChange = { 
                        pw = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "비밀번호를 입력해주세요",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                MoneyMateTextField(
                    text = confirmPw,
                    onValueChange = { 
                        confirmPw = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(
                            text = "확인을 위해 비밀번호를 다시 입력해주세요",
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
                    modifier = Modifier
                        .width(320.dp)
                        .padding(bottom = 30.dp),
                    text = "다음",
                    containerColor = MoneyMateTheme.colors.deepBlue,
                    contentColor = MoneyMateTheme.colors.white,
                ) {
                    if (!pw.isValidPassword()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("영문, 숫자를 포함하여 8자 이상 입력해주세요")
                        }
                    } else if (pw != confirmPw) {
                        scope.launch {
                            snackbarHostState.showSnackbar("비밀번호가 일치하지 않습니다")
                        }
                    } else {
                        viewModel.saveSignupPassword(
                            password = pw
                        ) {
                            onNext()
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 30.dp, end = 30.dp, bottom = 100.dp)
        )
    }
}

