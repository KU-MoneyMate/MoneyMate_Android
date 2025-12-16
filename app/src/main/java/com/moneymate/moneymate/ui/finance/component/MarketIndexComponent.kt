package com.moneymate.moneymate.ui.finance.component

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
            indexList.forEachIndexed { index, it ->
                MarketIndexItem(
                    modifier = Modifier,
                    indexName = it.indexName,
                    indexValue = it.indexValue,
                    profitRate = it.profitRate,
                    fluctuation = it.fluctuation,
                    status = it.status
                )
                if (index < indexList.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 0.5.dp,
                        color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            text = "환율(원)",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        if (currencyList.isNotEmpty()) {
            currencyList.forEachIndexed { index, it ->
                MarketIndexItem(
                    modifier = Modifier,
                    indexName = it.indexName,
                    indexValue = it.indexValue,
                    profitRate = it.profitRate,
                    fluctuation = it.fluctuation,
                    status = it.status
                )
                if (index < currencyList.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 0.5.dp,
                        color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f)
                    )
                }
            }
        }
    }
}


@Composable
fun MarketIndexItem(
    modifier: Modifier = Modifier,
    indexName: String,
    indexValue: String,
    profitRate: String,
    fluctuation: String,
    status: String
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
            text = indexName,
            style = MoneyMateTheme.typography.head_04_SB_14,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier,
                text = indexValue,
                style = MoneyMateTheme.typography.head_04_SB_14,
            )
            Text(
                modifier = Modifier,
                text = "${fluctuation}(${profitRate}%)",
                style = MoneyMateTheme.typography.head_04_SB_14,
                color = when (status) {
                    "RISING", "UPPER_LIMIT" -> MoneyMateTheme.colors.stockRed
                    "FALLING", "LOWER_LIMIT" -> MoneyMateTheme.colors.stockBlue
                    else -> MoneyMateTheme.colors.darkGray
                }
            )
        }
    }
}

data class MarketIndexData(
    val indexName: String,
    val indexValue: String,
    val profitRate: String, // 등락율
    val fluctuation: String, // 전일과의 차이
    val status: String // 상승, 하락, 보합 (RISING, FALLING, UNCHANGED)
)

@Preview(showBackground = true)
@Composable
private fun MarketIndexComponentPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        val indexList = listOf(
            MarketIndexData("코스피", "2,450.25", "+1.25%", "+30.50", "RISING"),
            MarketIndexData("코스닥", "800.50", "-0.75%", "-6.00", "FALLING"),
            MarketIndexData("S&P 500", "4,500.75", "+0.50%", "+22.50", "RISING"),
            MarketIndexData("나스닥", "13,200.30", "+2.10%", "+270.00", "RISING"),
            MarketIndexData("다우존스", "34,000.10", "-0.30%", "-102.00", "FALLING")
        )
        val currencyList = listOf(
            MarketIndexData("USD/KRW", "1,200.50", "+0.10%", "+1.20", "RISING"),
            MarketIndexData("EUR/KRW", "1,350.75", "-0.20%", "-2.70", "FALLING"),
            MarketIndexData("JPY/KRW", "1,100.30", "+0.05%", "+0.55", "RISING"),
            MarketIndexData("CNY/KRW", "180.25", "-0.15%", "-0.27", "FALLING"),
            MarketIndexData("GBP/KRW", "180.25", "-0.15%", "-0.27", "FALLING")
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