package com.moneymate.moneymate.ui.asset.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.R
import com.moneymate.moneymate.data.dto.account.response.TransactionInfo
import com.moneymate.moneymate.ui.asset.AssetViewModel
import com.moneymate.moneymate.ui.asset.component.TransactionHistoryItem
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import com.moneymate.moneymate.util.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionHistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: AssetViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val scrollState = rememberScrollState()
//    val transactionInfos: List<TransactionInfo> = listOf(
//        TransactionInfo(
//            date = "2025-05-01",
//            time = "12:34:56",
//            outAmount = 4000,
//            inAmount = 0,
//            afterBalance = 45000,
//            destination = "CU 건국대점"
//        ),
//        TransactionInfo(
//            date = "2025-05-01",
//            time = "13:34:56",
//            outAmount = 4000,
//            inAmount = 0,
//            afterBalance = 45000,
//            destination = "학생식당"
//        ),
//        TransactionInfo(
//            date = "2025-05-01",
//            time = "14:34:56",
//            outAmount = 0,
//            inAmount = 1000000,
//            afterBalance = 45000,
//            destination = "카카오페이"
//        ),
//
//        TransactionInfo(
//            date = "2025-05-03",
//            time = "13:34:56",
//            outAmount = 4000,
//            inAmount = 0,
//            afterBalance = 45000,
//            destination = "CU 건국대점"
//        ),
//    )
    val transactionInfos = viewModel.transactionHistory.collectAsStateWithLifecycle().value
    // Pass your data here
    val grouped = transactionInfos.groupBy { it.date }

    Column(modifier = modifier
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
                        text = "거래 내역 조회",
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
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = "KB국민은행 57370104098146",
                style = MoneyMateTheme.typography.head_03_R_16.copy(
                    color = MoneyMateTheme.colors.darkGray
                )
            )
            Text(
                text = "888,888원",
                style = MoneyMateTheme.typography.head_01_B_24
            )
            Spacer(modifier = Modifier.size(20.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = MoneyMateTheme.colors.deepBlue
            )
            Spacer(modifier = Modifier.size(20.dp))
            // 날짜별로 거래내역 그룹화
            grouped.forEach { (date, transactionsOnDate) ->
                Text(
                    text = formatDate(date),
                    style = MoneyMateTheme.typography.head_03_SB_16.copy(
                        color = MoneyMateTheme.colors.deepBlue
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                transactionsOnDate
                    .sortedByDescending { it.time }
                    .forEach { transactions ->
                        TransactionHistoryItem(
                            modifier = Modifier,
                            transactionInfo = transactions
                        )
                    }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun TransactionHistoryScreenPreview() {
    TransactionHistoryScreen(
        modifier = Modifier,
        onNavigateBack = {}
    )
}