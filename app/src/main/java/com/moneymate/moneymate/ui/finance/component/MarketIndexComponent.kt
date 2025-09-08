package com.moneymate.moneymate.ui.finance.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.moneymate.moneymate.util.toDecimalFormat
import okhttp3.internal.http2.flowcontrol.WindowCounter

@Composable
fun MarketIndexComponent(
    modifier: Modifier = Modifier,
    indexList: List<MarketIndexData> = emptyList(),
    currencyList: List<MarketIndexData> = emptyList()
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "주요 지수",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        if (indexList.isNotEmpty()) {
            indexList.forEach { it ->
                MarketIndexItem(
                    modifier = Modifier,
                    indexName = it.indexName,
                    indexValue = it.indexValue,
                    profitRate = it.profitRate
                )
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "환율",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        if (currencyList.isNotEmpty()) {
            currencyList.forEach { it ->
                MarketIndexItem(
                    modifier = Modifier,
                    indexName = it.indexName,
                    indexValue = it.indexValue,
                    profitRate = it.profitRate
                )
            }
        }
    }
}


@Composable
fun MarketIndexItem(
    modifier: Modifier = Modifier,
    indexName: String,
    indexValue: String,
    profitRate: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(45.dp),
                painter = painterResource(R.drawable.img_dummy_asset),
                contentDescription = "asset icon",
                tint = MoneyMateTheme.colors.lightGray
            )
            Spacer(modifier = Modifier.size(17.dp))
            Text(text = indexName, style = MoneyMateTheme.typography.head_04_SB_14)
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier,
                text = indexValue,
                style = MoneyMateTheme.typography.head_04_SB_14,
            )
            Text(
                modifier = Modifier,
                text = profitRate,
                style = MoneyMateTheme.typography.head_04_SB_14,
                color = MoneyMateTheme.colors.stockRed
            )
        }
    }
}

data class MarketIndexData(
    val indexName: String,
    val indexValue: String,
    val profitRate: String
)

@Preview(showBackground = true)
@Composable
private fun MarketIndexComponentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        val indexList = listOf(
            MarketIndexData("코스피", "2,450.25", "+1.25%"),
            MarketIndexData("코스닥", "800.50", "-0.75%"),
            MarketIndexData("S&P 500", "4,500.75", "+0.50%"),
            MarketIndexData("나스닥", "13,200.30", "+2.10%"),
            MarketIndexData("다우존스", "34,000.10", "-0.30%")
        )
        val currencyList = listOf(
            MarketIndexData("USD/KRW", "1,200.50", "+0.10%"),
            MarketIndexData("EUR/KRW", "1,350.75", "-0.20%"),
            MarketIndexData("JPY/KRW", "1,100.30", "+0.05%"),
            MarketIndexData("CNY/KRW", "180.25", "-0.15%")
        )
         MarketIndexComponent(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 20.dp),
             indexList = indexList,
             currencyList = currencyList
         )
    }
}