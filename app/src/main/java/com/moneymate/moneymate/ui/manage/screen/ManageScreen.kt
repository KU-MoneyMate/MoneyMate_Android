package com.moneymate.moneymate.ui.manage.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun ManageScreen(
    modifier: Modifier = Modifier,
    viewModel: ManageViewModel = hiltViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "ManageScreen",
            style = MoneyMateTheme.typography.head_02_B_20,
            color = MoneyMateTheme.colors.black
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}