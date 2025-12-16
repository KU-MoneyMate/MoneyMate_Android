package com.moneymate.moneymate.ui.finance.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.finance.component.NewsCategoryButton
import com.moneymate.moneymate.ui.finance.component.NewsItem
import com.moneymate.moneymate.ui.finance.component.PublisherData
import com.moneymate.moneymate.ui.finance.component.PublisherProvider
import com.moneymate.moneymate.ui.finance.component.PublisherProvider.publisherList
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsPublisherHomeScreen(
    modifier: Modifier,
    publisher: String,
    viewModel: FinanceViewModel = hiltViewModel(),
    onArticleClick: (String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val publisherData = PublisherProvider.publisherList.find { it.enum == publisher }

    val categoryState = remember {
        publisherData?.category?.toMutableStateList()
    }

    LaunchedEffect(Unit) {
        if (!categoryState.isNullOrEmpty()) {
            val selected = categoryState.firstOrNull { it.isSelected }
            selected?.let {
                viewModel.getCategoryNews(publisher, it.categoryName)
            }
        }
    }

    val categoryNews = viewModel.categoryNewsList.value
    val scrollState = rememberScrollState()

    if (publisherData != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MoneyMateTheme.colors.white)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp)
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_mypage_arrow),
                        contentDescription = "뒤로가기",
                        modifier = Modifier
                            .rotate(180f)
                            .clickable { onNavigateBack() }
                    )
                }
                Box(
                    modifier = Modifier.weight(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "경제 뉴스 조회",
                        color = MoneyMateTheme.colors.darkGray,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            fontSize = 20.sp
                        )
                    )
                }
                Box(modifier = Modifier.weight(1f))
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 33.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        painter = painterResource(publisherData.image),
                        contentDescription = "publisher logo"
                    )
                    Spacer(modifier = Modifier.width(19.dp))
                    Text(
                        text = publisherData.publisherName,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                            fontSize = 32.sp,
                            color = MoneyMateTheme.colors.black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = publisherData.introduction,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        color = MoneyMateTheme.colors.black
                    )
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 33.dp, end = 33.dp)
                    .background(MoneyMateTheme.colors.white)
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (categoryState != null) {
                    categoryState.forEachIndexed { index, category ->
                        NewsCategoryButton(
                            category = category.categoryName,
                            isSelected = category.isSelected,
                            onClick = {
                                categoryState.replaceAll { it.copy(isSelected = false) }
                                categoryState[index] = category.copy(isSelected = true)

                                viewModel.getCategoryNews(publisher, category.categoryName)

                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(17.dp))

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 28.dp)
                    .weight(1f)
            ) {
                items(categoryNews.size) { index ->
                    val article = categoryNews[index]
                    val isLast = index == categoryNews.lastIndex

                    NewsItem(
                        title = article.title,
                        url = article.link,
                        isLastArticle = isLast,
                        onArticleClick = {
                            onArticleClick(article.link)
                        }
                    )
                }
            }

        }
    }

}