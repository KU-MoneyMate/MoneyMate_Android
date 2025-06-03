package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun LoginScreen(
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MoneyMateTheme.colors.white),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier
                .padding(horizontal = 39.dp)
        ){
            Spacer(modifier = Modifier.height(75.dp))
            Text(
                text = "아이디를 입력해주세요",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold))
                )
            )
            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(hint = "ID / E-mail")
            Spacer(modifier = Modifier.height(8.dp))
            CustomTextField(hint = "Password")
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { //TODO 로그인
                          },
                modifier = Modifier
                    .width(320.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MoneyMateTheme.colors.deepBlue),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "로그인",
                    color = Color.White,
                    style = TextStyle (
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold))
                    )
                )
            }

            OutlinedButton(
                onClick = {
                    //TODO 회원가입
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.2.dp, MoneyMateTheme.colors.deepBlue),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MoneyMateTheme.colors.deepBlue)
            ) {
                Text(text = "회원가입",
                    color = MoneyMateTheme.colors.deepBlue,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold))
                    )
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    hint: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(color = MoneyMateTheme.colors.neutral100, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 28.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = hint,
            color = MoneyMateTheme.colors.neutral500,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold))
            ),

        )
    }
}
