package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.common.MoneyMateMenuButton
import com.moneymate.moneymate.ui.mypage.MyPageViewModel
import com.moneymate.moneymate.ui.mypage.component.PasswordVerificationDialog
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    onUserInfoClick : () -> Unit,
    onResetPasswordClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit,
    authViewModel : AuthViewModel = hiltViewModel(),
    viewModel: MyPageViewModel = hiltViewModel()
    // 필요한 기능에 따라 다른 viewmodel 추가 될 수 있음
) {
    val showDialog: Boolean = viewModel.showPasswordDialog.collectAsState().value
    val isLoading:Boolean = viewModel.isLoading.collectAsState().value
    val isPasswordError: Boolean = viewModel.isPasswordError.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(vertical = 16.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showDialog) {
            PasswordVerificationDialog(
                onDismissRequest = viewModel::onDialogDismissed,
                onVerify = viewModel::verifyCurrentPassword,
                isLoading = isLoading,
                isError = isPasswordError
            )
        }

        // 3. 화면 이동 이벤트 처리 (Side Effect)
        LaunchedEffect(key1 = Unit) {
            viewModel.verificationSuccessEvent.collect {
                // 비밀번호 재설정 검증 성공 시
                onResetPasswordClick()
            }
            // TODO: ViewModel에서 회원 탈퇴 성공 시 별도의 이벤트 플로우를 통해 onAccountDeletedSuccess() 등의 콜백을 호출하도록 처리
            // 예시: viewModel.deletionSuccessEvent.collect { onDeleteAccountClick() }
        }

        MoneyMateMenuButton("계정 정보 조회 및 업데이트", 67, onUserInfoClick)
        MoneyMateMenuButton("비밀번호 재설정", 67, onClick = viewModel::onPasswordResetClicked)
        MoneyMateMenuButton(
            text="로그아웃",
            height = 67,
            onClick = {authViewModel.logout(onLogoutSuccess = onLogoutClick)}
        )
        MoneyMateMenuButton("회원 탈퇴", 67, onClick = viewModel::onDeleteAccountClicked)
    }
}
