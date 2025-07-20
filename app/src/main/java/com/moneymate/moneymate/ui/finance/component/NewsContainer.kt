package com.moneymate.moneymate.ui.finance.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsContainer(
    news: NewsContainerData,
    onAddClick: (String) -> Unit,
    onArticleClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MoneyMateTheme.colors.white)
            .height(224.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .padding(horizontal = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = news.publisher.publisherName,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular))
                )
            )
            Row(
                modifier = Modifier
                    .clickable { onAddClick(news.publisher.publisherName) }, //언론사 홈으로
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "더보기",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular))
                    )
                )
                Icon(
                    painter = painterResource(R.drawable.ic_mypage_arrow),
                    contentDescription = "더보기",
                    modifier = Modifier
                        .size(16.dp)
                )
            }

        }
        Column(
            modifier = Modifier
                .padding(bottom = 12.dp, start = 28.dp, end = 28.dp)
        ) {

            news.articles.take(4).forEachIndexed { index, article ->
                val isLast = index == news.articles.take(4).lastIndex
                NewsItem(
                    title = article.title,
                    url = article.url,
                    isLastArticle = isLast,
                    onArticleClick = { onArticleClick(article.url) } //기사 링크로 이동
                )
            }
        }
    }
}