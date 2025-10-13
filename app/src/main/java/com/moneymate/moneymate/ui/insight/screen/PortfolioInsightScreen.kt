package com.moneymate.moneymate.ui.insight.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.ui.insight.PortfolioInsightViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun PortfolioInsightScreen(
    modifier: Modifier = Modifier,
    viewModel: PortfolioInsightViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MoneyMateTheme.colors.backgroundWhite)
    ) {

    }
}
