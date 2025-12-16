package com.moneymate.moneymate.ui.mypage.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun MyPageConfirmDialog(
    myPageDialogType : MypageDialogType,
    onDismissRequest: () -> Unit,
    onClick : () -> Unit,
    isLoading: Boolean,
) {
    val dialogTitle: String
    val dialogText: String

    when (myPageDialogType) {
        MypageDialogType.CONFIRM_LOGOUT -> {
            dialogTitle = "로그아웃"
            dialogText = "로그아웃 하시겠습니까?"
        }
        MypageDialogType.CONFIRM_DELETE_ACCOUNT -> {
            dialogTitle = "계정 탈퇴"
            dialogText = "MoneyMate를 탈퇴하시겠습니까?\n탈퇴한 계정은 복구할 수 없습니다."
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = MoneyMateTheme.colors.white,
        title = { Text(
            text = dialogTitle,
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                fontSize = 20.sp
            )) },
        text = {
            Column {
                Text(dialogText)
                Spacer(Modifier.height(16.dp))
            }
        },
        confirmButton = {
            Button(
                onClick = onClick,
                enabled = !isLoading,
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


enum class MypageDialogType {
    CONFIRM_LOGOUT,
    CONFIRM_DELETE_ACCOUNT

}

@Preview(showBackground = true)
@Composable
private fun MyPageConfirmDialogPreview() {
    MyPageConfirmDialog(
        MypageDialogType.CONFIRM_DELETE_ACCOUNT,
        {},
        {},
        false
    )
}