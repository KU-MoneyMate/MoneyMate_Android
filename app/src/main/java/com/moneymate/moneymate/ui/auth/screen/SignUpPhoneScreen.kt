package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
fun SignUpPhoneScreen(
    modifier: Modifier,
    viewModel: AuthViewModel,
    onNext: () -> Unit
) {
    var phone1 by rememberSaveable { mutableStateOf("") }
    var phone2 by rememberSaveable { mutableStateOf("") }
    var phone3 by rememberSaveable { mutableStateOf("") }

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

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MoneyMateTextField(
                    text = phone1,
                    onValueChange = { phone1 = it },
                    modifier = Modifier.width(100.dp),
                    placeholder = {
                        Text(
                            text = "010",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.img_signin_dash),
                    contentDescription = "phone num dash",
                    modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
                )

                MoneyMateTextField(
                    text = phone2,
                    onValueChange = { phone2 = it },
                    modifier = Modifier.width(100.dp),
                    placeholder = {
                        Text(
                            text = "1234",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    }
                )

                Image(
                    painter = painterResource(id = R.drawable.img_signin_dash),
                    contentDescription = "phone num dash",
                    modifier = Modifier.size(16.dp).padding(horizontal = 4.dp)
                )

                MoneyMateTextField(
                    text = phone3,
                    onValueChange = { phone3 = it },
                    modifier = Modifier.width(100.dp),
                    placeholder = {
                        Text(
                            text = "1234",
                            style = MoneyMateTheme.typography.body_01_M_14
                        )
                    }
                )
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
                text = "인증번호 전송",
                modifier = Modifier
                    .width(320.dp)
                    .padding(bottom = 30.dp)
            ) {
                val formattedPhoneNumber = "${phone1}-${phone2}-${phone3}"
                viewModel.saveSignupPhone(formattedPhoneNumber) {
                    onNext()
                }
            }
        }
    }
}
