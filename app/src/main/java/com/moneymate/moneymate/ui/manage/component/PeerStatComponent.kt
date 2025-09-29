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
import com.moneymate.moneymate.ui.finance.component.MarketIndexItem
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun PeerStatComponent(
    modifier: Modifier = Modifier,
    peerAssetData: PeerStatData,
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
        // TODO: 각 항목

        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Composable
fun PeerStatItem(
    modifier: Modifier = Modifier,
    statName: String,
    statValue: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
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
            text = statValue,
            style = MoneyMateTheme.typography.head_04_SB_14,
        )
    }
}

data class PeerStatData(
    val average: String,
    val median: String,
    val userValue: String?
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
                average = "100000",
                median = "50000",
                userValue = "60000"
            ),
            peerConsumptionData = PeerStatData(
                average = "100000",
                median = "50000",
                userValue = null
            ),
            peerIncomeData = PeerStatData(
                average = "100000",
                median = "50000",
                userValue = null
            )
        )
    }
}