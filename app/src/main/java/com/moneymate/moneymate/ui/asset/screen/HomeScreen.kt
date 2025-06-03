package com.moneymate.moneymate.ui.asset.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.asset.component.AccountContainer
import com.moneymate.moneymate.ui.asset.component.AssetContainer
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddAccountClick: (String) -> Unit,
    onAddAssetClick: (String) -> Unit,
    onAccountItemClick: () -> Unit,
    viewModel: AssetViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    // viewmodel의 totalAccounts 에서 accountType으로 구분된 리스트들
    val depositList = viewModel.totalAccounts.collectAsStateWithLifecycle().value.filter { it.type == "입출금" }
    val savingsList = viewModel.totalAccounts.collectAsStateWithLifecycle().value.filter { it.type == "예적금" }
    val securitiesList = viewModel.totalAccounts.collectAsStateWithLifecycle().value.filter { it.type == "증권" }
    val realEstateList = viewModel.totalAssets.collectAsStateWithLifecycle().value.filter { it.type == "부동산" }
    val investmentList = viewModel.totalAssets.collectAsStateWithLifecycle().value.filter { it.type == "투자" }

    Column(modifier = modifier.fillMaxSize()
        .background(MoneyMateTheme.colors.backgroundWhite)
        .padding(horizontal = 26.dp)
        .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        // 입출금 계좌
        AccountContainer(
            name = "입출금 계좌",
            accountList = depositList,
            onItemClick = {
                onAccountItemClick()
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
            onItemClick = {
                onAccountItemClick()
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
            onItemClick = {
                onAccountItemClick()
            },
            onAddClick = {
                onAddAccountClick("증권 계좌")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        //부동산
        AssetContainer(
            name = "부동산",
            assetList = realEstateList,
            onAddClick = {
                onAddAssetClick("부동산")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        // 투자
        AssetContainer(
            name = "투자 자산",
            assetList = investmentList,
            onAddClick = {
                onAddAssetClick("투자 자산")
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onAddAccountClick = {},
        onAddAssetClick = {},
        onAccountItemClick = {}
    )
}