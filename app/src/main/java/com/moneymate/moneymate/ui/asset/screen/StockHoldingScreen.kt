package com.moneymate.moneymate.ui.asset.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.asset.response.StockInfo
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockHoldingScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val stockList = viewModel.totalStocks.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
    ) {
        TopAppBar(
            modifier = Modifier,
            title = {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        modifier = Modifier
                            .clickable { onNavigateBack() },
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back icon"
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "보유 주식 목록",
                        style = MoneyMateTheme.typography.head_02_B_20
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MoneyMateTheme.colors.white
            )
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState)
        ) {
            // accountName을 기준으로 stockList를 그룹화하여 각 증권사별로 StockCompanyContainer 생성
            stockList.value
                .groupBy { it.accountName }
                .forEach { (accountName, stocks) ->
                    StockCompanyContainer(
                        accountName = accountName,
                        stockList = stocks,
                        getIconUrl = { ticker -> viewModel.getStockIconUrl(ticker) }
                    )
                }
        }
    }
}

@Composable
fun StockItemContainer(
    stockName: String,
    ticker: String,
    quantity: String,
    totalPrice: String,
    profitRate: String,
    iconUrl: String
) {
    val profitAmount = (totalPrice.toDouble() * (1 - 1 / (1 + profitRate.toDouble() / 100)))
    var priceColor = MoneyMateTheme.colors.black
    var profitSign = "."

    if (profitAmount.toDouble() > 0) {
        priceColor = MoneyMateTheme.colors.stockRed
        profitSign = "+"
    } else {
        priceColor = MoneyMateTheme.colors.stockBlue
        profitSign = "-"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(81.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
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
                    Log.d("StockItemContainer", "[$ticker] 이미지 로딩 중: $iconUrl")
                },
                onSuccess = { state ->
                    Log.d("StockItemContainer", "[$ticker] 이미지 로딩 성공!")
                    Log.d("StockItemContainer", "[$ticker] DataSource: ${state.result.dataSource}")
                },
                onError = { state ->
                    Log.e("StockItemContainer", "[$ticker] 이미지 로딩 실패!")
                    Log.e("StockItemContainer", "[$ticker] URL: $iconUrl")
                    Log.e("StockItemContainer", "[$ticker] Error: ${state.result.throwable.message}")
                    state.result.throwable.printStackTrace()
                }
            )
            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "$stockName($ticker)",
                    color = MoneyMateTheme.colors.black,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = quantity + "주",
                    color = MoneyMateTheme.colors.black,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        fontSize = 12.sp
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = NumberFormat.getNumberInstance(Locale.US)
                    .format(totalPrice.toDouble().toLong()) + "원",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(2.dp))

            Row {
                Text(
                    text = profitSign + " " + NumberFormat.getNumberInstance(Locale.US)
                        .format(abs(profitAmount)) + "원 ",
                    color = priceColor,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontSize = 12.sp
                    )
                )
                Text(
                    text = "($profitRate %)",
                    color = priceColor,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontSize = 12.sp
                    )
                )
            }


        }
    }

}

@Composable
fun StockCompanyContainer(
    accountName: String,
    stockList: List<StockInfo>,
    getIconUrl: (String) -> String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text(
            text = accountName,
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MoneyMateTheme.colors.deepBlue)
        )
    }
    stockList.forEach { item ->
        StockItemContainer(
            stockName = item.stockName,
            ticker = item.ticker,
            quantity = item.quantity,
            totalPrice = item.totalPrice,
            profitRate = item.profit,
            iconUrl = getIconUrl(item.ticker)
        )
    }

}