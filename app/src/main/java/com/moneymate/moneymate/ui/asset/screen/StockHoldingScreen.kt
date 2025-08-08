package com.moneymate.moneymate.ui.asset.screen

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.asset.response.StockInfo
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.abs

@Composable
fun StockHoldingScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_mypage_arrow),
                    contentDescription = "뒤로가기",
                    modifier = Modifier
                        .rotate(180f)
                        .clickable { onNavigateBack() }
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "보유 주식 목록",
                    color = MoneyMateTheme.colors.darkGray,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        fontSize = 20.sp
                    )
                )
            }
            Box(modifier = Modifier.weight(1f))
        }

//        stockItemContainer("테슬라","TSL", "2", "130000",  "30")
//        stockItemContainer("테슬라","TSL", "2", "70000",  "-30")

        stockCompanyContainer(
            accountName = "키움증권",
            stockList = listOf(
                StockInfo("키움증권","테슬라","TSL", "2", "130000",  "30"),
                StockInfo("키움증권","테슬라","TSL", "2", "130000",  "30"),)
        )
        stockCompanyContainer(
            accountName = "삼성증권",
            stockList = listOf(
                StockInfo("삼성증권","테슬라","TSL", "2", "70000",  "-30"),
                StockInfo("삼성증권","테슬라","TSL", "2", "70000",  "-30"),)
        )

    }
}

@Composable
fun stockItemContainer(
    //image : Int,
    stockName : String,
    ticker : String,
    quantity : String,
    totalPrice : String,
    profitRate : String,
){
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

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(81.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(45.dp),
                painter = painterResource(R.drawable.img_dummy_asset),
                contentDescription = "asset icon",
                tint = MoneyMateTheme.colors.lightGray
            )
            Spacer(modifier = Modifier.width(20.dp))

            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = stockName+"("+ticker+")",
                    color = MoneyMateTheme.colors.black,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = quantity+"주",
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
                text = NumberFormat.getNumberInstance(Locale.US).format(totalPrice.toInt())+"원",
                color = MoneyMateTheme.colors.black,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(2.dp))

            Row {
                Text(
                    text = profitSign+" "+NumberFormat.getNumberInstance(Locale.US).format(abs(profitAmount))+"원 ",
                    color = priceColor,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        fontSize = 12.sp
                    )
                )
                Text (
                    text = "("+profitRate+" %)",
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
fun stockCompanyContainer(
    accountName: String,
    stockList: List<StockInfo>,
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
    ) {
        Text (
            text = accountName,
            color = MoneyMateTheme.colors.darkGray,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                fontSize = 18.sp
            )
        )
        Spacer (modifier = Modifier.height(8.dp))
        Spacer (
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MoneyMateTheme.colors.deepBlue)
        )
    }
    stockList.forEach { item ->
        stockItemContainer(
            stockName = item.stockName,
            ticker = item.ticker,
            quantity = item.quantity,
            totalPrice = item.totalPrice,
            profitRate = item.profit
        )
    }

}