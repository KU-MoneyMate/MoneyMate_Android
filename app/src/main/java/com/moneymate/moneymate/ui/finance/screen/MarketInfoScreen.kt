package com.moneymate.moneymate.ui.finance.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.MarketInfoViewModel
import com.moneymate.moneymate.ui.finance.component.MarketIndexComponent
import com.moneymate.moneymate.ui.finance.component.MarketIndexData
import com.moneymate.moneymate.ui.finance.component.MarketStockData
import com.moneymate.moneymate.ui.finance.component.StockMarketComponent
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketInfoScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewModel: MarketInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dropdownList = listOf("경제 지표", "국내 주식", "해외 주식")
    // 국내 시장 목록
    val koreanMarkets = listOf("KOSPI", "KOSDAQ")
    // 해외 시장 목록
    val foreignMarkets = listOf("NASDAQ", "NYSE", "AMEX")
//    // 보여줄 인덱스 목록 (응답의 indexName에 해당)
//    val indexInfoList = listOf("코스피", "코스닥", "S&P500", "다우존스", "나스닥 종합", "니케이 225", "상해종합", "항셍")
//    // 보여줄 환율 목록 (응답의 name에 해당)
//    val exchangeRateList = listOf("미국 USD", "유럽 EUR", "일본 JPY", "중국 CNY", "영국 GBP", "홍콩 HKD")
    var selectedDropdownMenu by rememberSaveable { mutableStateOf(dropdownList[0]) }
    var selectedMarket by rememberSaveable { mutableStateOf("KOSPI") }
    val scrollState = rememberScrollState()

    val marketTop20List = if (selectedDropdownMenu == "국내 주식") {
        uiState.domesticMarketCap?.stocks?.map { stock ->
            MarketStockData(
                stockName = stock.stockName,
                stockValue = stock.closePrice,
                profitRate = stock.fluctuationsRatio,
                fluctuation = stock.compareToPreviousClosePrice,
                status = stock.compareToPreviousPrice.name
            )
        } ?: emptyList()
    } else {
        uiState.foreignMarketCap?.stocks?.map { stock ->
            MarketStockData(
                stockName = stock.stockName,
                stockValue = stock.closePrice ?: "error",
                profitRate = stock.fluctuationsRatio ?: "error",
                fluctuation = stock.compareToPreviousClosePrice ?: "error",
                status = stock.compareToPreviousPrice.name
            )
        } ?: emptyList()
    }

    val increasingTop20List = if (selectedDropdownMenu == "국내 주식") {
        uiState.domesticMarketRising?.stocks?.map { stock ->
            MarketStockData(
                stockName = stock.stockName,
                stockValue = stock.closePrice,
                profitRate = stock.fluctuationsRatio,
                fluctuation = stock.compareToPreviousClosePrice,
                status = stock.compareToPreviousPrice.name
            )
        } ?: emptyList()
    } else {
        uiState.foreignMarketRising?.stocks?.map { stock ->
            MarketStockData(
                stockName = stock.stockName,
                stockValue = stock.closePrice ?: "error",
                profitRate = stock.fluctuationsRatio ?: "error",
                fluctuation = stock.compareToPreviousClosePrice ?: "error",
                status = stock.compareToPreviousPrice.name
            )
        } ?: emptyList()
    }

    val decreasingTop20List = if (selectedDropdownMenu == "국내 주식") {
        uiState.domesticMarketFalling?.stocks?.map { stock ->
            MarketStockData(
                stockName = stock.stockName,
                stockValue = stock.closePrice,
                profitRate = stock.fluctuationsRatio,
                fluctuation = stock.compareToPreviousClosePrice,
                status = stock.compareToPreviousPrice.name
            )
        } ?: emptyList()
    } else {
        uiState.foreignMarketFalling?.stocks?.map { stock ->
            MarketStockData(
                stockName = stock.stockName,
                stockValue = stock.closePrice ?: "error",
                profitRate = stock.fluctuationsRatio ?: "error",
                fluctuation = stock.compareToPreviousClosePrice ?: "error",
                status = stock.compareToPreviousPrice.name
            )
        } ?: emptyList()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MoneyMateTheme.colors.deepBlue
            )
            Log.d("MarketInfoScreen", "Loading...")
        }

        Column(
            modifier = Modifier.fillMaxSize()
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
                                when (selectedDropdownMenu) {
                                    dropdownList[0] -> Log.d(
                                        "StockMarketScreen",
                                        "selectedDropdownMenu: $selectedDropdownMenu"
                                    )

                                    dropdownList[1] -> {
                                        selectedMarket = koreanMarkets[0]
                                        viewModel.loadMarketData(selectedMarket, true)
                                        Log.d(
                                            "StockMarketScreen",
                                            "selectedDropdownMenu: $selectedDropdownMenu, selectedMarket: $selectedMarket"
                                        )
                                    }

                                    dropdownList[2] -> {
                                        selectedMarket = foreignMarkets[0]
                                        viewModel.loadMarketData(selectedMarket, false)
                                        Log.d(
                                            "StockMarketScreen",
                                            "selectedDropdownMenu: $selectedDropdownMenu, selectedMarket: $selectedMarket"
                                        )
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
                            indexList = uiState.marketIndexes.map { it ->
                                MarketIndexData(
                                    indexName = it.indexName,
                                    indexValue = it.closePrice,
                                    profitRate = it.fluctuationsRatio,
                                    fluctuation = it.compareToPreviousClosePrice,
                                    status = it.compareToPreviousPrice.name
                                )
                            },
                            currencyList = uiState.exchangeRates?.result?.map { it ->
                                MarketIndexData(
                                    indexName = it.name,
                                    indexValue = it.closePrice,
                                    profitRate = it.fluctuationsRatio,
                                    fluctuation = it.fluctuations,
                                    status = it.fluctuationsType.name
                                )
                            } ?: emptyList()
                        )
                        Log.d("MarketInfoScreen", "selectedDropdownMenu: $selectedDropdownMenu")
                    }

                    "국내 주식" -> {
                        // 시장 선택 버튼
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            koreanMarkets.forEach { market ->
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
                                        containerColor = if (selectedMarket == market) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                                        contentColor = if (selectedMarket == market) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                                    ),
                                    onClick = {
                                        selectedMarket = market
                                        viewModel.loadMarketData(selectedMarket, true)
                                        Log.d(
                                            "StockMarketScreen",
                                            "selectedMarket: $selectedMarket"
                                        )
                                    },
                                ) {
                                    Text(text = market)
                                }
                                if (market != koreanMarkets.last()) {
                                    Spacer(modifier = Modifier.size(4.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        // 카테고리별 Top 20 항목들
                        StockMarketComponent(
                            modifier = Modifier.fillMaxWidth(),
                            marketName = selectedMarket,
                            marketTop20List = marketTop20List,
                            increasingTop20List = increasingTop20List,
                            decreasingTop20List = decreasingTop20List
                        )
                    }

                    "해외 주식" -> {
                        // 시장 선택 버튼
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            foreignMarkets.forEach { market ->
                                Button(
                                    modifier = Modifier
                                        .border(
                                            width = 2.dp,
                                            color = MoneyMateTheme.colors.deepBlue,
                                            shape = RoundedCornerShape(25.dp)
                                        )
                                        .size(width = 90.dp, height = 42.dp),
                                    shape = RoundedCornerShape(25.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (selectedMarket == market) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                                        contentColor = if (selectedMarket == market) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                                    ),
                                    onClick = {
                                        selectedMarket = market
                                        viewModel.loadMarketData(selectedMarket, false)
                                        Log.d(
                                            "StockMarketScreen",
                                            "selectedMarket: $selectedMarket"
                                        )
                                    },
                                ) {
                                    Text(text = market)
                                }
                                if (market != foreignMarkets.last()) {
                                    Spacer(modifier = Modifier.size(4.dp))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        // 카테고리별 Top 20 항목들
                        StockMarketComponent(
                            modifier = Modifier.fillMaxWidth(),
                            marketName = selectedMarket,
                            marketTop20List = marketTop20List,
                            increasingTop20List = increasingTop20List,
                            decreasingTop20List = decreasingTop20List
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MarketInfoScreenPreview() {
    MarketInfoScreen(
        modifier = Modifier.fillMaxSize(),
        onNavigateBack = {}
    )
}