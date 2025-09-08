package com.moneymate.moneymate.ui.finance.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.component.MarketIndexComponent
import com.moneymate.moneymate.ui.finance.component.MarketIndexData
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import java.time.LocalDate
import kotlin.collections.listOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockMarketScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dropdownList = listOf("경제 지표", "국내 주식", "해외 주식")
    var selectedDropdownMenu by rememberSaveable { mutableStateOf(dropdownList[0]) }
    val koreanMarkets = listOf("KOSPI", "KOSDAQ")
    val foreignMarkets = listOf("NASDAQ", "NYSE", "AMEX")
    var selectedMarket by rememberSaveable { mutableStateOf("KOSPI") }
    val scrollState = rememberScrollState()


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
                            .clickable {
                                onNavigateBack()
                            },
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back icon"
                    )
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "증시 정보",
                        style = MoneyMateTheme.typography.head_02_B_20
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MoneyMateTheme.colors.white
            )
        )
        Spacer(modifier = Modifier.size(20.dp))
        // 드롭다운 메뉴
        Box(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .clickable { dropdownExpanded = !dropdownExpanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedDropdownMenu,
                    style = MoneyMateTheme.typography.head_03_B_16
                )
                Spacer(modifier = Modifier.size(4.dp))
                Icon(
                    modifier = Modifier
                        .rotate(270f),
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "dropdown icon"
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .background(color = MoneyMateTheme.colors.backgroundWhite),
                expanded = dropdownExpanded,
                offset = DpOffset(0.dp, 0.dp),
                onDismissRequest = { dropdownExpanded = false }
            ) {
                dropdownList.forEachIndexed { index, selectionOption ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(color = MoneyMateTheme.colors.backgroundWhite),
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedDropdownMenu = dropdownList[index]
                            dropdownExpanded = false
                            when(selectedDropdownMenu){
                                dropdownList[0] -> Log.d("StockMarketScreen", "selectedDropdownMenu: $selectedDropdownMenu")
                                dropdownList[1] -> {
                                    selectedMarket = koreanMarkets[0]
                                    Log.d("StockMarketScreen", "selectedDropdownMenu: $selectedDropdownMenu, selectedMarket: $selectedMarket")
                                }
                                dropdownList[2] -> {
                                    selectedMarket = foreignMarkets[0]
                                    Log.d("StockMarketScreen", "selectedDropdownMenu: $selectedDropdownMenu, selectedMarket: $selectedMarket")
                                }
                            }
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        // 시장 선택을 위한 버튼 컴포넌트
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            when (selectedDropdownMenu) {
                "경제 지표" -> {
                    // 지수 정보 조회 컴포넌트
                    MarketIndexComponent(
                        modifier = Modifier.fillMaxWidth(),
                        indexList = listOf(
                            MarketIndexData("코스피", "2,450.25", "+1.25%"),
                            MarketIndexData("코스닥", "800.50", "-0.75%"),
                            MarketIndexData("S&P 500", "4,500.75", "+0.50%"),
                            MarketIndexData("나스닥", "13,200.30", "+2.10%"),
                            MarketIndexData("다우존스", "34,000.10", "-0.30%")
                        ),
                        currencyList = listOf(
                            MarketIndexData("USD/KRW", "1,200.50", "+0.10%"),
                            MarketIndexData("EUR/KRW", "1,350.75", "-0.20%"),
                            MarketIndexData("JPY/KRW", "1,100.30", "+0.05%"),
                            MarketIndexData("CNY/KRW", "180.25", "-0.15%")
                        )
                    )
                }

                "국내 주식" -> {
                    // 시장 선택 버튼
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Button(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = MoneyMateTheme.colors.deepBlue,
                                    shape = RoundedCornerShape(25.dp)
                                )
                                .size(width = 72.dp, height = 42.dp),
                            shape = RoundedCornerShape(25.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
//                    containerColor = if (selectedMarket == koreanMarkets[0]) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
//                    contentColor = if (selectedMarket == koreanMarkets[0]) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                            ),
                            onClick = {
//                    selectedDuration = 1
//                    currentMonth = LocalDate.now()
//                    Log.d("AssetStatisticsScreen", "selectedDuration: $selectedDuration")
                                // TODO: 로직 구현
                            },
                        ) {
                            Text(text = "KOSPI")
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                        Button(
                            modifier = Modifier
                                .border(
                                    width = 2.dp,
                                    color = MoneyMateTheme.colors.deepBlue,
                                    shape = RoundedCornerShape(25.dp)
                                )
                                .size(width = 72.dp, height = 42.dp),
                            shape = RoundedCornerShape(25.dp),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
//                    containerColor = if (selectedMarket == koreanMarkets[1]) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
//                    contentColor = if (selectedMarket == koreanMarkets[1]) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                            ),
                            onClick = {
//                    selectedDuration = 1
//                    currentMonth = LocalDate.now()
//                    Log.d("AssetStatisticsScreen", "selectedDuration: $selectedDuration")
                                // TODO: 로직 구현
                            },
                        ) {
                            Text(text = "KOSDAQ")
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    // 카테고리별 Top 20 항목들
                }

                "해외 주식" -> {

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StockMarketScreenPreview() {
    StockMarketScreen(
        modifier = Modifier.fillMaxSize(),
        onNavigateBack = {}
    )
}