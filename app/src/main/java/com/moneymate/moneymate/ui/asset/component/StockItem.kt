package com.moneymate.moneymate.ui.asset.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.asset.response.StockInfo
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.moneymate.moneymate.util.toDecimalFormat

@Composable
fun StockItem(
    modifier: Modifier = Modifier,
    stockName: String,
    ticker: String,
    stockValue: String,
    profitRate: String,
    iconUrl: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp, horizontal = 35.dp)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(iconUrl)
                    .decoderFactory(SvgDecoder.Factory())
                    .crossfade(true)
                    .build(),
                contentDescription = "$stockName icon",
                modifier = Modifier.size(45.dp),
                contentScale = ContentScale.Fit,
                error = painterResource(R.drawable.img_dummy_asset),
                placeholder = painterResource(R.drawable.img_dummy_asset),
                onLoading = {
                    Log.d("StockItem", "[$ticker] 이미지 로딩 중: $iconUrl")
                },
                onSuccess = { state ->
                    Log.d("StockItem", "[$ticker] 이미지 로딩 성공!")
                    Log.d("StockItem", "[$ticker] DataSource: ${state.result.dataSource}")
                },
                onError = { state ->
                    Log.e("StockItem", "[$ticker] 이미지 로딩 실패!")
                    Log.e("StockItem", "[$ticker] URL: $iconUrl")
                    Log.e("StockItem", "[$ticker] Error: ${state.result.throwable.message}")
                    state.result.throwable.printStackTrace()
                }
            )
            Spacer(modifier = Modifier.size(17.dp))
            Column(
                modifier = Modifier,
            ) {
                Text(text = stockName+"(${ticker})", style = MoneyMateTheme.typography.body_02_R_12)
                Text(
                    text = stockValue.toDouble().toLong().toDecimalFormat()+"원",
                    style = MoneyMateTheme.typography.head_02_B_20
                )
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = if(profitRate.startsWith("-")) "$profitRate%" else "+$profitRate%",
            style = MoneyMateTheme.typography.body_03_M_20,
            color = when {
                profitRate.toDouble() == 0.0 -> MoneyMateTheme.colors.black
                profitRate.startsWith("-") -> MoneyMateTheme.colors.stockBlue
                else -> MoneyMateTheme.colors.stockRed
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StockItemPreview() {
    StockItem(
        modifier = Modifier,
        stockName = "테슬라",
        ticker = "TSLA",
        stockValue = "1000000.0",
        profitRate = "5.0",
        iconUrl = ""
    )
}