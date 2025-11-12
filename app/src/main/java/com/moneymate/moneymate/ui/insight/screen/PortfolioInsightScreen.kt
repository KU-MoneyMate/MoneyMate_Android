package com.moneymate.moneymate.ui.insight.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichTextStyle
import com.halilibo.richtext.ui.material3.Material3RichText
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.insight.PortfolioInsightViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioInsightScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    viewModel: PortfolioInsightViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

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
                        text = "AI 포트폴리오 분석",
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
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center
        ) {

            when (uiState.value.isLoading) {
                true -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = MoneyMateTheme.colors.deepBlue
                        )
                    }
                }
                false -> {
                    CompositionLocalProvider(
                        LocalTextStyle provides MoneyMateTheme.typography.insightArticleStyle
                    ) {
                        Material3RichText(
                            style = RichTextStyle(
                                headingStyle = { level, textStyle ->
                                    when (level) {
                                        0 -> textStyle.copy( // H1
                                            fontSize = 24.sp,
                                            lineHeight = 32.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        1 -> textStyle.copy( // H2
                                            fontSize = 20.sp,
                                            lineHeight = 28.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        2 -> textStyle.copy( // H3
                                            fontSize = 18.sp,
                                            lineHeight = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        else -> textStyle.copy(
                                            fontSize = 16.sp,
                                            lineHeight = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            )
                        ) {
                            Markdown(content = uiState.value.insight)
                        }
                    }
                }
            }
        }
    }
}
