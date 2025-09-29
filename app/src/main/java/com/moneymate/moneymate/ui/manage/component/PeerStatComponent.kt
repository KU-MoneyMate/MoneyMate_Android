package com.moneymate.moneymate.ui.manage.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.moneymate.moneymate.util.toDecimalFormat

@Composable
fun PeerStatComponent(
    modifier: Modifier = Modifier,
    peerAssetData: PeerStatData,
    userTotalAsset: Long,
    peerConsumptionData: PeerStatData,
    peerIncomeData: PeerStatData
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "자산 비교",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        PeerStatItem(statName = "자산 평균값", statValue = peerAssetData.average)
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f))
        PeerStatItem(statName = "자산 중앙값", statValue = peerAssetData.median)
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f))
        PeerStatItem(statName = "내 자산", statValue = userTotalAsset)
        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = "월 소비 비교",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        PeerStatItem(statName = "소비 평균값", statValue = peerConsumptionData.average)
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f))
        PeerStatItem(statName = "소비 중앙값", statValue = peerConsumptionData.median)
        Spacer(modifier = Modifier.size(30.dp))

        Text(
            text = "소득 비교",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        PeerStatItem(statName = "소득 평균값", statValue = peerIncomeData.average)
        HorizontalDivider(modifier = Modifier.fillMaxWidth(), thickness = 0.5.dp, color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f))
        PeerStatItem(statName = "소득 중앙값", statValue = peerIncomeData.median)
    }
}

@Composable
fun PeerStatItem(
    modifier: Modifier = Modifier,
    statName: String,
    statValue: Long
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            text = statName,
            style = MoneyMateTheme.typography.head_04_SB_14,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            modifier = Modifier,
            text = statValue.toDecimalFormat()+"만 원",
            style = MoneyMateTheme.typography.head_04_SB_14,
        )
    }
}

data class PeerStatData(
    val average: Long,
    val median: Long,
)

@Preview(showBackground = true)
@Composable
private fun PeerStatComponentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        PeerStatComponent(
            modifier = Modifier,
            peerAssetData = PeerStatData(
                average = 100000,
                median = 50000,
            ),
            userTotalAsset = 60000,
            peerConsumptionData = PeerStatData(
                average = 100000,
                median = 50000,
            ),
            peerIncomeData = PeerStatData(
                average = 100000,
                median = 50000,
            )
        )
    }
}