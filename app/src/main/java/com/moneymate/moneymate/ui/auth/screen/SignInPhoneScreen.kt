package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun SignInPhoneScreen(
    modifier: Modifier
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
                text = "전화번호를 입력해주세요",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold))
                )
            )

            Spacer(modifier = Modifier.height(33.dp))

            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                CustomTextFieldForPhoneNum("010")
                Image(
                    painter = painterResource(id = R.drawable.img_signin_dash),
                    contentDescription = "phone num dash",
                    modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
                )
                CustomTextFieldForPhoneNum("1234")
                Image(
                    painter = painterResource(id = R.drawable.img_signin_dash),
                    contentDescription = "phone num dash",
                    modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
                )
                CustomTextFieldForPhoneNum("1234")
            }

        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { //TODO 다음
                },
                modifier = Modifier
                    .width(320.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MoneyMateTheme.colors.deepBlue),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text(text = "인증번호 전송",
                    color = Color.White,
                    style = TextStyle (
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold))
                    )
                )
            }

        }
    }
}

@Composable
fun CustomTextFieldForPhoneNum (
    hint: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(100.dp)
            .height(44.dp)
            .background(color = MoneyMateTheme.colors.neutral100, shape = RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = hint,
            color = MoneyMateTheme.colors.neutral500,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular))
            ),

            )
    }
}
