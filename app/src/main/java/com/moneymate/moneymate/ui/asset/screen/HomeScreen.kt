package com.moneymate.moneymate.ui.asset.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.data.dto.asset.response.StockInfo
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.asset.component.AccountContainer
import com.moneymate.moneymate.ui.asset.component.AssetContainer
import com.moneymate.moneymate.ui.asset.component.StockContainer
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddAccountClick: (String) -> Unit,
    onAddAssetClick: (String) -> Unit,
    onStockClick: () -> Unit,
    onAccountItemClick: (AccountInfo) -> Unit,
    viewModel: AssetViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    // 전체 계좌
    val totalAccounts = viewModel.totalAccounts.collectAsStateWithLifecycle().value
    // 전체 자산
    val totalAssets = viewModel.totalAssets.collectAsStateWithLifecycle().value
    // 전체 주식
    val totalStocks = viewModel.totalStocks.collectAsStateWithLifecycle().value
    // 주식 로딩 상태
    val isStocksLoading = viewModel.isStocksLoading.collectAsStateWithLifecycle().value

    LaunchedEffect(Unit) {
        viewModel.getAssetList()
        viewModel.getTotalAccountList()
    }

    // 계좌 유형별 리스트
    val depositList = totalAccounts.filter { it.type == "입출금" }
    val savingsList = totalAccounts.filter { it.type == "예적금" }
    val securitiesList = totalAccounts.filter { it.type == "증권" }

    Column(modifier = modifier.fillMaxSize()
        .background(MoneyMateTheme.colors.backgroundWhite)
        .padding(horizontal = 20.dp)
        .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        // 입출금 계좌
        AccountContainer(
            name = "입출금 계좌",
            accountList = depositList,
            onItemClick = { accountInfo ->
                onAccountItemClick(accountInfo)
            },
            onAddClick = {
                onAddAccountClick("입출금 계좌")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        // 적금 계좌
        AccountContainer(
            name = "예적금 계좌",
            accountList = savingsList,
            onItemClick = { accountInfo ->
                onAccountItemClick(accountInfo)
            },
            onAddClick = {
                onAddAccountClick("적금 계좌")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        // 증권 계좌
        AccountContainer(
            name = "증권 계좌",
            accountList = securitiesList,
            onItemClick = { accountInfo ->
                onAccountItemClick(accountInfo)
            },
            onAddClick = {
                onAddAccountClick("증권 계좌")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        // 투자
        AssetContainer(
            name = "투자 자산",
            assetList = totalAssets,
            onAddClick = {
                onAddAssetClick("투자 자산")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        // 주식
        if (isStocksLoading) {
            StockContainer(
                stockList = emptyList(),
                onNavigateToStockDetail = onStockClick,
                getIconUrl = { "" },
                isLoading = true
            )
        } else {
            StockContainer(
                stockList = totalStocks,
                onNavigateToStockDetail = onStockClick,
                getIconUrl = { ticker -> viewModel.getStockIconUrl(ticker) },
                isLoading = false
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onAddAccountClick = {},
        onAddAssetClick = {},
        onAccountItemClick = {},
        onStockClick = {}
    )
}