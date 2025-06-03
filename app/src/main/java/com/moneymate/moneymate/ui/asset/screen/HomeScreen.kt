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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.asset.component.AccountContainer
import com.moneymate.moneymate.ui.asset.component.AssetContainer
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddAccountClick: (String) -> Unit,
    onAddAssetClick: (String) -> Unit,
    onAccountItemClick: (AccountInfo) -> Unit,
    viewModel: AssetViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    // 전체 계좌
    val totalAccounts = viewModel.totalAccounts.collectAsStateWithLifecycle().value
//    val totalAccounts = listOf(
//        AccountInfo(
//            "1","2","KB 국민은행", "입출금", "11111111",1000000
//        ),
//        AccountInfo(
//            "2","3","토스뱅크", "입출금", "22222222",100000
//        ),
//        AccountInfo(
//            "3","3","삼성증권", "증권", "3333333333",400000
//        ),
//        AccountInfo(
//            "4","3","청년도약계좌", "예적금", "44444444",500000
//        ),
//    )
    // 전체 자산
    val totalAssets = viewModel.totalAssets.collectAsStateWithLifecycle().value
    // 계좌 유형별 리스트
    val depositList = totalAccounts.filter { it.type == "입출금" }
    val savingsList = totalAccounts.filter { it.type == "예적금" }
    val securitiesList = totalAccounts.filter { it.type == "증권" }
    // 자산별 리스트
    val realEstateList = totalAssets.filter { it.type == "부동산" }
    val investmentList = totalAssets.filter { it.type == "투자" }

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