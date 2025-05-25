package com.moneymate.moneymate.ui.asset.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetViewModel = hiltViewModel()
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "HomeScreen",
            style = MoneyMateTheme.typography.head_02_B_20,
            color = MoneyMateTheme.colors.black
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}