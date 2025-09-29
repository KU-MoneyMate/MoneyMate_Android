package com.moneymate.moneymate.ui.manage.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.moneymate.moneymate.ui.manage.PeerStatViewModel
import com.moneymate.moneymate.ui.manage.component.PeerStatComponent
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeerAssetStatisticsScreen(
    modifier: Modifier = Modifier,
    viewModel: PeerStatViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dropdownList = listOf("20대", "30대", "40대", "50대", "60대 이상")
    var selectedDropdownMenu by rememberSaveable { mutableStateOf(dropdownList[0]) }

    val peerAssetStat = viewModel.peerAssetStat.collectAsStateWithLifecycle()
    val totalAsset = viewModel.totalAsset.collectAsStateWithLifecycle()
    val peerConsumptionStat = viewModel.peerConsumptionStat.collectAsStateWithLifecycle()
    val peerIncomeStat = viewModel.peerIncomeStat.collectAsStateWithLifecycle()

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
                        text = "또래 통계",
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
                            
                            // 연령대에 맞는 age 값 계산
                            val age = when (selectedDropdownMenu) {
                                "20대" -> 20
                                "30대" -> 30
                                "40대" -> 40
                                "50대" -> 50
                                "60대 이상" -> 60
                                else -> 20
                            }
                            viewModel.getPeerAssetStat(age)
                            viewModel.getPeerConsumptionStat(age)
                            viewModel.getPeerIncomeStat(age)
                            Log.d("PeerAssetStatisticsScreen", "selectedDropdownMenu: $selectedDropdownMenu, age: $age")
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            PeerStatComponent(
                modifier = Modifier.fillMaxWidth(),
                peerAssetData = peerAssetStat.value,
                userTotalAsset = totalAsset.value,
                peerConsumptionData = peerConsumptionStat.value,
                peerIncomeData = peerIncomeStat.value
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PeerAssetStatisticsScreenPreview() {
    PeerAssetStatisticsScreen(
        modifier = Modifier,
        onNavigateBack = {}
    )
}