package com.moneymate.moneymate.ui.insight.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.ui.common.MoneyMateMenuButton

@Composable
fun InsightMenuScreen(
    modifier: Modifier = Modifier,
    onNavigateToNewsInsight: () -> Unit,
    onNavigateToPortfolioInsight: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(vertical = 20.dp, horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoneyMateMenuButton("뉴스 인사이트", 67, onNavigateToNewsInsight)
        MoneyMateMenuButton("포트폴리오 인사이트", 67, onNavigateToPortfolioInsight)
    }
}

@Preview(showBackground = true)
@Composable
private fun InsightMenuScreenPreview() {
    InsightMenuScreen(
        modifier = Modifier,
        onNavigateToNewsInsight = { },
        onNavigateToPortfolioInsight = { },
        onNavigateBack = { }
    )
}