package com.moneymate.moneymate.ui.manage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.common.MoneyMateMenuButton
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun ManageScreen(
    modifier: Modifier = Modifier,
    onRetireClick : () -> Unit,
    onAssetStatisticsClick: () -> Unit,
    onSpendingStatisticsClick : () -> Unit,
    viewModel: ManageViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(vertical = 16.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoneyMateMenuButton("노후 설계 시뮬레이션", 67, onRetireClick)
        MoneyMateMenuButton("또래 자산 통계 조회하기", 67, onRetireClick) /*나중에 함수 바꾸기*/
        MoneyMateMenuButton("자산 변동 통계 조회하기", 67, onAssetStatisticsClick)
        MoneyMateMenuButton("소비 통계 조회하기", 67, onSpendingStatisticsClick)
    }
}