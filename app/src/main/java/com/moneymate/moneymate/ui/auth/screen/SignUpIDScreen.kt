package com.moneymate.moneymate.ui.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavHostController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.BottomFullWidthButton
import com.moneymate.moneymate.ui.common.MoneyMateTextField
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun SignUpIDScreen(
    modifier: Modifier,
    navController: NavHostController
) {
    var id by rememberSaveable { mutableStateOf("") }

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
                text = "아이디를 입력해주세요",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold))
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "영문 8자 이상",
                color = MoneyMateTheme.colors.neutral500,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            MoneyMateTextField(
                text = id,
                onValueChange = { id = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "아이디를 입력해주세요.",
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
                text = "다음",
                modifier = Modifier.width(320.dp).padding(bottom = 30.dp)
            ) {
                navController.navigate(Route.SignUpPW.route)
            }
        }
    }
}
