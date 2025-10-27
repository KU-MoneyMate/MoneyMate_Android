package com.moneymate.moneymate.ui.asset.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.asset.response.StockInfo
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun StockContainer(
    modifier: Modifier = Modifier,
    stockList : List<StockInfo>,
    onNavigateToStockDetail : () -> Unit,
    getIconUrl: (String) -> String
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MoneyMateTheme.colors.white)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.size(22.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "주식",
                    style = MoneyMateTheme.typography.head_02_B_20
                )
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onNavigateToStockDetail()
                        }
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(180f)
                            .align(Alignment.Center),
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "arrow",
                    )
                }
            }
            Spacer(modifier = Modifier.size(22.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MoneyMateTheme.colors.deepBlue
            )
        }
        if (stockList.isNotEmpty()){
            for(stock in stockList) {
                StockItem(
                    stockName = stock.stockName,
                    ticker = stock.ticker,
                    stockValue = stock.totalPrice,
                    profitRate = stock.profit,
                    iconUrl = getIconUrl(stock.ticker)
                )
            }
        } else {
            Spacer(modifier = Modifier.size(28.dp))
            Text(
                text = "등록된 자산이 없습니다.",
                style = MoneyMateTheme.typography.head_03_SB_16
            )
            Spacer(modifier = Modifier.size(28.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun StockContainerPreview() {
    val stockList = listOf(
        StockInfo("키움증권", "테슬라", "TSLA", "2", "130000", "30"),
        StockInfo("삼성증권", "애플", "AAPL", "5", "150000", "-20")
    )
    StockContainer(
        stockList = stockList,
        onNavigateToStockDetail = {},
        getIconUrl = { "" }
    )
}