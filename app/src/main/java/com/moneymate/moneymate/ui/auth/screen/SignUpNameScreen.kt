package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
fun SignUpNameScreen(
    modifier: Modifier,
    viewModel: AuthViewModel,
    onNext: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var birth by rememberSaveable { mutableStateOf("") }

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
                text = "이름과 생년월일을",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold))
                )
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "입력해주세요",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold))
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            MoneyMateTextField(
                text = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "이름을 입력해주세요",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {keyboardFocusManager.moveFocus(FocusDirection.Down)})
            )
            Spacer(modifier = Modifier.height(8.dp))
            MoneyMateTextField(
                text = birth,
                onValueChange = { birth = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "생년월일을 입력해주세요 ex)19980102",
                        style = MoneyMateTheme.typography.body_01_M_14
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {keyboardFocusManager.clearFocus()})
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
                text = "다음",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 30.dp),
            ) {
                val formattedBirth = "${birth.substring(0, 4)}-${birth.substring(4, 6)}-${birth.substring(6, 8)}"
                viewModel.saveSignupName(name) {
                    viewModel.saveSignupBirthday(formattedBirth) {
                        onNext()
                    }
                }
            }
        }
    }
}

