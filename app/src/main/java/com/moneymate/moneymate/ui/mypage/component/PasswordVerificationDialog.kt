package com.moneymate.moneymate.ui.mypage.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun PasswordVerificationDialog(
    onDismissRequest: () -> Unit,
    onVerify: (password: String) -> Unit,
    isLoading: Boolean,
    isError: Boolean
) {

    var passwordInput by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = MoneyMateTheme.colors.white,
        title = { Text(
            text = "현재 비밀번호 확인",
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 20.sp
            )) },
        text = {
            Column {
                Text(
                    "보안을 위해 현재 비밀번호를 입력해 주세요.")
                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    value = passwordInput,
                    onValueChange = { passwordInput = it },
                    label = { Text("현재 비밀번호") },
                    visualTransformation = PasswordVisualTransformation(),
                    enabled = !isLoading,
                )
                if (isError) {
                    Text(
                        text = "비밀번호가 일치하지 않습니다.",
                        color = MoneyMateTheme.colors.stockRed, // 색상 테마 사용
                        style = TextStyle(fontSize = 14.sp) // 폰트 스타일 지정
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onVerify(passwordInput) }, // ViewModel의 함수 호출
                enabled = passwordInput.isNotEmpty() && !isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoneyMateTheme.colors.deepBlue,
                    contentColor = MoneyMateTheme.colors.white
                )
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismissRequest,
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoneyMateTheme.colors.white,
                    contentColor = MoneyMateTheme.colors.darkGray
                )
            ) {
                Text("취소")
            }
        }
    )
}