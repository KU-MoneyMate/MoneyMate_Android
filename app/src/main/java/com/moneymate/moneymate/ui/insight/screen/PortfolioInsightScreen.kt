package com.moneymate.moneymate.ui.insight.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
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
        when (uiState.value.isLoading) {
            true -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = Modifier.size(80.dp),
                            painter = painterResource(id = R.drawable.ic_moneymatelogo),
                            contentDescription = "MoneyMate Logo",
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        LinearProgressIndicator(
                            color = MoneyMateTheme.colors.deepBlue,
                            trackColor = MoneyMateTheme.colors.neutral300
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "AI가 포트폴리오를 분석하고 있습니다",
                            style = MoneyMateTheme.typography.body_01_M_16,
                            color = MoneyMateTheme.colors.deepBlue,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "잠시만 기다려주세요",
                            style = MoneyMateTheme.typography.body_01_M_16,
                            color = MoneyMateTheme.colors.deepBlue,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            false -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp)
                        .verticalScroll(state = scrollState),
                ) {
                    Spacer(modifier = Modifier.size(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                        ) {
                            Text(
                                text = "분석일자 : "+ uiState.value.insightDate.ifBlank { "정보 없음" },
                                style = MoneyMateTheme.typography.body_01_M_14,
                            )
                            Text(
                                text = "남은 횟수 ${3 - uiState.value.dailyRequest}회",
                                style = MoneyMateTheme.typography.body_01_M_14,
                            )
                        }
                        Button(
                            modifier = Modifier,
                            contentPadding = PaddingValues(8.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MoneyMateTheme.colors.deepBlue,
                                contentColor = MoneyMateTheme.colors.white,
                                disabledContainerColor = MoneyMateTheme.colors.disabled
                            ),
                            enabled = !uiState.value.isLoading && uiState.value.dailyRequest < 3,
                            onClick = { viewModel.getPortfolioInsight() }
                        ) {
                            Text(
                                text = "분석하기",
                                style = MoneyMateTheme.typography.body_01_M_16,
                                color = MoneyMateTheme.colors.white
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))

                    // 내용이 없을 때는 중앙 정렬, 있을 때는 일반 표시
                    if (uiState.value.insight.isBlank() && uiState.value.insightDate.isBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "아직 포트폴리오 분석을 하지 않았습니다.\n\n분석을 요청해주세요.",
                                style = MoneyMateTheme.typography.body_01_M_16,
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
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
}
