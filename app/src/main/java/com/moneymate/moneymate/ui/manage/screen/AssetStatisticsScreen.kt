package com.moneymate.moneymate.ui.manage.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.manage.component.AssetStatisticsGraph
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetStatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: ManageViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    var currentMonth by rememberSaveable { mutableStateOf(LocalDate.now()) }
    var selectedDuration by rememberSaveable { mutableIntStateOf(1) }
    var selectedAssetType by rememberSaveable { mutableStateOf("전체") }
    val scrollState = rememberScrollState()

    // 자산 변동 데이터
    val assetStatHistory = viewModel.assetStatHistory.collectAsStateWithLifecycle()

    // 필터링된 데이터를 저장할 상태
    var filteredData by remember(assetStatHistory.value, selectedDuration, currentMonth) {
        mutableStateOf(
            assetStatHistory.value.filter { item ->
                val itemDate = item.date.split("-").map { it.toInt() }.let { (year, month) ->
                    LocalDate.of(year, month, 1)
                }

                when (selectedDuration) {
                    1 -> {
                        // 1년 선택 시: 선택된 연도의 데이터만 표시
                        itemDate.year == currentMonth.year
                    }

                    5, 10 -> {
                        // 5년, 10년 선택 시: 표시 범위에 해당하는 데이터만 표시
                        val monthsToSubtract = if (selectedDuration == 5) 60 else 120
                        val startDate = currentMonth.minusMonths(monthsToSubtract.toLong())

                        // startDate부터 currentMonth까지의 데이터만 포함
                        !itemDate.isBefore(startDate) && !itemDate.isAfter(currentMonth)
                    }

                    else -> true
                }
            }
        )
    }

    val modelProducer = remember { CartesianChartModelProducer() }
    LaunchedEffect(filteredData) {
        modelProducer.runTransaction {
            if (filteredData.isNotEmpty()) {
                val x = filteredData.indices.map { it.toDouble() }
                val y = filteredData.map { item ->
                    item.totalPrice.toDouble()
                }
                Log.d("AssetStatisticsScreen", "x: $x")
                lineSeries {
                    series(
                        x = x,
                        y = y
                    )
                }
            } else {
                // Clear the chart when there's no data
                Log.d("AssetStatisticsScreen", "filterData Empty")
            }
        }
    }

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
                        text = "자산 변동 통계",
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
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .background(MoneyMateTheme.colors.white)
        ) {
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "자산 변동 통계 조회입니다.\n원하는 옵션을 선택해서 결과를 조회해보세요.",
                style = MoneyMateTheme.typography.head_03_R_16
            )
            // 조회기간, 자산 종류
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "조회 기간",
                    style = MoneyMateTheme.typography.head_03_SB_16
                )
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                            containerColor = if (selectedDuration == 1) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedDuration == 1) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedDuration = 1
                            currentMonth = LocalDate.now()
                            Log.d("AssetStatisticsScreen", "selectedDuration: $selectedDuration")
                        },
                    ) {
                        Text(text = "1년")
                    }
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
                            containerColor = if (selectedDuration == 5) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedDuration == 5) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedDuration = 5
                            currentMonth = LocalDate.now()
                            Log.d("AssetStatisticsScreen", "selectedDuration: $selectedDuration")
                        },
                    ) {
                        Text(text = "5년")
                    }
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
                            containerColor = if (selectedDuration == 10) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedDuration == 10) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedDuration = 10
                            currentMonth = LocalDate.now()
                            Log.d("AssetStatisticsScreen", "selectedDuration: $selectedDuration")
                        },
                    ) {
                        Text(text = "10년")
                    }
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "자산 종류",
                    style = MoneyMateTheme.typography.head_03_SB_16
                )
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier
                        .horizontalScroll(scrollState),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
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
                            containerColor = if (selectedAssetType == "전체") MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedAssetType == "전체") MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedAssetType = "전체"
                            viewModel.getAssetStatsHistory("total")
                        },
                    ) {
                        Text(text = "전체")
                    }
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
                            containerColor = if (selectedAssetType == "입출금") MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedAssetType == "입출금") MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedAssetType = "입출금"
                            viewModel.getAssetStatsHistory("withdrawal")
                                  },
                    ) {
                        Text(text = "입출금")
                    }
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
                            containerColor = if (selectedAssetType == "적금") MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedAssetType == "적금") MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedAssetType = "적금"
                            viewModel.getAssetStatsHistory("deposit")
                                  },
                    ) {
                        Text(text = "적금")
                    }
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
                            containerColor = if (selectedAssetType == "증권") MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                            contentColor = if (selectedAssetType == "증권") MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue
                        ),
                        onClick = {
                            selectedAssetType = "증권"
                            viewModel.getAssetStatsHistory("stock")
                                  },
                    ) {
                        Text(text = "증권")
                    }
                }
            }

            // 월 표시
            Spacer(modifier = Modifier.size(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "previous month",
                    modifier = Modifier.clickable {
                        // 데이터의 첫 번째 날짜보다 이전으로는 이동하지 않도록 제한
                        val firstDate = assetStatHistory.value.firstOrNull()?.let { data ->
                            val (year, month) = data.date.split("-").map { it.toInt() }
                            LocalDate.of(year, month, 1)
                        }

                        when (selectedDuration) {
                            1 -> {
                                // 1년 선택 시 연 단위로 이동하되 첫 날짜의 연도까지만 이동 가능
                                if (firstDate == null || currentMonth.minusYears(1).year >= firstDate.year) {
                                    currentMonth = currentMonth.minusYears(1)
                                }
                            }

                            else -> {
                                // 5년, 10년 선택 시 월 단위로 이동하되 표시 범위의 시작일이 첫 날짜 이전으로는 이동 불가
                                val monthsToSubtract = if (selectedDuration == 5) 60 else 120
                                if (firstDate == null || currentMonth.minusMonths(1)
                                        .minusMonths((monthsToSubtract - 1).toLong())
                                        .isAfter(firstDate) || currentMonth.minusMonths(1)
                                        .minusMonths((monthsToSubtract - 1).toLong())
                                        .isEqual(firstDate)
                                ) {
                                    currentMonth = currentMonth.minusMonths(1)
                                }
                            }
                        }
                        Log.d("AssetStatisticsScreen", "currentMonth: $currentMonth")
                    }
                )
                Text(
                    text = run {
                        val startDate = assetStatHistory.value.firstOrNull()?.let { data ->
                            val (year, month) = data.date.split("-").map { it.toInt() }
                            LocalDate.of(year, month, 1)
                        }

                        when (selectedDuration) {
                            1 -> {
                                // 1년 선택 시 - 데이터의 첫 날짜가 현재 표시 연도보다 이후라면 첫 날짜의 연도를 표시
                                if (startDate != null && startDate.year > currentMonth.year) {
                                    "${startDate.year}년"
                                } else {
                                    "${currentMonth.year}년"
                                }
                            }

                            else -> {
                                val monthsToSubtract = if (selectedDuration == 5) 60 else 120

                                // 시작 날짜 계산 - 데이터의 첫 날짜와 현재 날짜에서 monthsToSubtract를 뺀 날짜 중 더 최근 날짜 선택
                                val displayStartDate = if (startDate != null) {
                                    val calculatedStart =
                                        currentMonth.minusMonths(monthsToSubtract.toLong())
                                    if (calculatedStart.isBefore(startDate)) startDate else calculatedStart
                                } else {
                                    currentMonth.minusMonths(monthsToSubtract.toLong())
                                }

                                "${displayStartDate.year}년 ${displayStartDate.monthValue}월 ~ ${currentMonth.year}년 ${currentMonth.monthValue}월"
                            }
                        }
                    },
                    style = MoneyMateTheme.typography.head_03_SB_16
                )
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = "next month",
                    modifier = Modifier
                        .rotate(180f)
                        .clickable {
                            // 현재 날짜 이후로는 이동하지 않도록 제한
                            when (selectedDuration) {
                                1 -> {
                                    // 1년 선택 시 연 단위로 이동
                                    if (currentMonth.plusYears(1).year <= LocalDate.now().year) {
                                        currentMonth = currentMonth.plusYears(1)
                                    }
                                }

                                else -> {
                                    // 5년, 10년 선택 시 월 단위로 이동
                                    if (currentMonth.isBefore(LocalDate.now().withDayOfMonth(1))) {
                                        currentMonth = currentMonth.plusMonths(1)
                                    }
                                }
                            }
                            Log.d("AssetStatisticsScreen", "currentMonth: $currentMonth")
                        }
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            AssetStatisticsGraph(
                modifier = Modifier
                    .fillMaxWidth(),
                modelProducer = modelProducer,
                dates = filteredData.map { it.date }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AssetStatisticsScreenPreview() {
    AssetStatisticsScreen() {

    }
}