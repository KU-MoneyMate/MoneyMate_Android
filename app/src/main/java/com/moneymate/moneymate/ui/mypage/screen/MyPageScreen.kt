package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.common.MoneyMateMenuButton
import com.moneymate.moneymate.ui.mypage.MyPageViewModel
import com.moneymate.moneymate.ui.mypage.component.MyPageConfirmDialog
import com.moneymate.moneymate.ui.mypage.component.PasswordVerificationDialog
import androidx.compose.runtime.getValue

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    onUserInfoClick : () -> Unit,
    onResetPasswordClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    authViewModel : AuthViewModel = hiltViewModel(),
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val showPasswordDialog by viewModel.showPasswordDialog.collectAsState()
    val confirmDialogType by viewModel.showConfirmDialog.collectAsState()
    val isLoading:Boolean = viewModel.isLoading.collectAsState().value
    val isPasswordError: Boolean = viewModel.isPasswordError.collectAsState().value

    confirmDialogType?.let { dialogType ->
        MyPageConfirmDialog(
            myPageDialogType = dialogType,
            onDismissRequest = viewModel::onConfirmDialogDismissed,
            onClick = viewModel::onConfirmDialog,
            isLoading = isLoading
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(vertical = 16.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showPasswordDialog) {
            PasswordVerificationDialog(
                onDismissRequest = viewModel::onPasswordDialogDismissed,
                onVerify = viewModel::verifyCurrentPassword,
                isLoading = isLoading,
                isError = isPasswordError
            )
        }

        LaunchedEffect(key1 = Unit) {
            // 비밀번호 재설정 검증 성공 시
            viewModel.verificationSuccessEvent.collect {
                onResetPasswordClick()
            }
        }

        // 로그아웃 이벤트 수신 처리
        LaunchedEffect(key1 = viewModel.logoutEvent) {
            viewModel.logoutEvent.collect {
                authViewModel.logout(onLogoutSuccess = onLogoutClick)
            }
        }

        MoneyMateMenuButton("계정 정보 조회 및 업데이트", 67, onUserInfoClick)
        MoneyMateMenuButton("비밀번호 재설정", 67, onClick = viewModel::onPasswordResetClicked)
        MoneyMateMenuButton(
            text="로그아웃",
            height = 67,
            onClick = viewModel::onLogoutClicked
        )
        MoneyMateMenuButton("회원 탈퇴", 67, onClick = viewModel::onDeleteAccountClicked)
    }
}
