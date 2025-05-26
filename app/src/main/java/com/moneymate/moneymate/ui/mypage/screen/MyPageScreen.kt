package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
    // 필요한 기능에 따라 다른 viewmodel 추가 될 수 있음
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "MyPageScreen",
            style = MoneyMateTheme.typography.head_02_B_20,
            color = MoneyMateTheme.colors.black
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}