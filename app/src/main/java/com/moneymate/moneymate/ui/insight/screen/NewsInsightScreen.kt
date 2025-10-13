package com.moneymate.moneymate.ui.insight.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneymate.moneymate.ui.insight.NewsInsightViewModel
import com.moneymate.moneymate.ui.insight.component.NewsSummaryItem
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsInsightScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsInsightViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MoneyMateTheme.colors.backgroundWhite)
    ) {
        when(uiState.value.isLoading){
            true -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            false -> {
                LazyColumn {
                    items(uiState.value.newsList){ it ->
                        NewsSummaryItem(
                            title = it.category,
                            content = it.content,
                            generatedTime = it.generatedTime
                        )
                    }
                }
            }
        }
    }

}