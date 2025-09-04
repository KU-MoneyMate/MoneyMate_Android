package com.moneymate.moneymate.ui.manage.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PeerAssetStatisticsScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Text(
            text = "또래 자산 통계 조회하기"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PeerAssetStatisticsScreenPreview() {
    PeerAssetStatisticsScreen(
        modifier = Modifier,
        onNavigateBack = {}
    )
}