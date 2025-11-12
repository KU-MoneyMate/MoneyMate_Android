package com.moneymate.moneymate.ui.insight.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.insight.NewsInsightViewModel
import com.moneymate.moneymate.ui.insight.component.NewsSummaryItem
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsInsightScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewModel: NewsInsightViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var dropdownExpanded by remember { mutableStateOf(false) }
    val dropdownList = listOf("증시", "부동산", "경제")
    var selectedDropdownMenu by rememberSaveable { mutableStateOf(dropdownList[0]) }

    // 선택된 카테고리에 해당하는 뉴스 아이템 찾기
    val selectedCategory = when (selectedDropdownMenu) {
        "증시" -> "stock"
        "부동산" -> "realestate"
        "경제" -> "economy"
        else -> "stock"
    }
    val selectedNewsItem = uiState.value.newsList.find { it.category == selectedCategory }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MoneyMateTheme.colors.white)
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
                        text = "AI 뉴스 요약",
                        style = MoneyMateTheme.typography.head_02_B_20
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MoneyMateTheme.colors.white
            )
        )
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
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            when (uiState.value.isLoading) {
                true -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                false -> {
                    selectedNewsItem?.let { newsItem ->
                        NewsSummaryItem(
                            title = newsItem.category,
                            content = newsItem.content,
                            generatedTime = newsItem.generatedTime
                        )
                    }
                }
            }
        }
    }

}