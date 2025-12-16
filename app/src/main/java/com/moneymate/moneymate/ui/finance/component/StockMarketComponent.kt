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
fun StockMarketComponent(
    modifier: Modifier = Modifier,
    marketName: String,
    marketTop20List: List<MarketStockData> = emptyList(),
    increasingTop20List: List<MarketStockData> = emptyList(),
    decreasingTop20List: List<MarketStockData> = emptyList()
) {
    val currency = when (marketName) {
        "KOSPI", "KOSDAQ" -> "원"
        "NYSE", "NASDAQ", "AMEX" -> "달러"
        else -> ""
    }
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = if(marketName == "KOSPI"|| marketName == "KOSDAQ") "국내 Top 20($currency)" else "$marketName Top 20($currency)",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        if (marketTop20List.isNotEmpty()) {
            marketTop20List.forEachIndexed { index, it ->
                StockMarketItem(
                    modifier = Modifier,
                    stockName = it.stockName,
                    stockValue = it.stockValue,
                    profitRate = it.profitRate,
                    fluctuation = it.fluctuation,
                    status = it.status
                )
                if (index < marketTop20List.lastIndex) {
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
            text = if(marketName == "KOSPI"|| marketName == "KOSDAQ") "국내 상승 Top 20($currency)" else "$marketName 상승 Top 20($currency)",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        if (increasingTop20List.isNotEmpty()) {
            increasingTop20List.forEachIndexed { index, it ->
                StockMarketItem(
                    modifier = Modifier,
                    stockName = it.stockName,
                    stockValue = it.stockValue,
                    profitRate = it.profitRate,
                    fluctuation = it.fluctuation,
                    status = it.status
                )
                if (index < increasingTop20List.lastIndex) {
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
            text = if(marketName == "KOSPI"|| marketName == "KOSDAQ") "국내 하락 Top 20($currency)" else "$marketName 하락 Top 20($currency)",
            style = MoneyMateTheme.typography.head_03_B_16
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        if (decreasingTop20List.isNotEmpty()) {
            decreasingTop20List.forEachIndexed { index, it ->
                StockMarketItem(
                    modifier = Modifier,
                    stockName = it.stockName,
                    stockValue = it.stockValue,
                    profitRate = it.profitRate,
                    fluctuation = it.fluctuation,
                    status = it.status
                )
                if (index < decreasingTop20List.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        thickness = 0.5.dp,
                        color = MoneyMateTheme.colors.lightGray.copy(alpha = 0.3f)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
    }
}


@Composable
fun StockMarketItem(
    modifier: Modifier = Modifier,
    stockName: String,
    stockValue: String,
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
            text = stockName,
            style = MoneyMateTheme.typography.head_04_SB_14,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier,
                text = stockValue,
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

data class MarketStockData(
    val stockName: String,
    val stockValue: String,
    val profitRate: String, // 등락율
    val fluctuation: String, // 전일과의 차이
    val status: String // 상승, 하락, 보합 (RISING, FALLING, UNCHANGED)
)

@Preview(showBackground = true)
@Composable
private fun StockMarketComponentPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StockMarketComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            marketName = "KOSPI",
            marketTop20List = listOf(
                MarketStockData("삼성전자", "65,000", "+1.25%", "+800", "RISING"),
                MarketStockData("SK하이닉스", "120,000", "+0.75%", "+900", "RISING"),
                MarketStockData("LG화학", "850,000", "-0.50%", "-4,000", "FALLING")
            ),
            increasingTop20List = listOf(
                MarketStockData("삼성전자", "65,000", "+1.25%", "+800", "RISING"),
                MarketStockData("SK하이닉스", "120,000", "+0.75%", "+900", "RISING"),
                MarketStockData("LG화학", "850,000", "-0.50%", "-4,000", "FALLING")
            ),
            decreasingTop20List = listOf(
                MarketStockData("삼성전자", "65,000", "+1.25%", "+800", "RISING"),
                MarketStockData("SK하이닉스", "120,000", "+0.75%", "+900", "RISING"),
                MarketStockData("LG화학", "850,000", "-0.50%", "-4,000", "FALLING")
            ),
        )
    }
}