package com.moneymate.moneymate.ui.finance.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.finance.FinanceViewModel
import com.moneymate.moneymate.ui.finance.component.ArticleData
import com.moneymate.moneymate.ui.finance.component.NewsContainer
import com.moneymate.moneymate.ui.finance.component.NewsContainerData
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    viewModel: FinanceViewModel = hiltViewModel(),
    onAddClick : (String) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    val dummyNewsData = listOf(
        NewsContainerData("pulisher",
            listOf(
                ArticleData("20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070", ""),
                ArticleData("20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070", ""),
                ArticleData("20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070", ""),
                ArticleData("20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070", ""))
        ), NewsContainerData("secondPublisher",
            listOf(ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","")
            )
        ),
        NewsContainerData("secondPublisher",
            listOf(ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","")
            )
        ),
        NewsContainerData("secondPublisher",
            listOf(ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",""),
                ArticleData("부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","")
            )
        ),
    )

/*    val dummyNewsSections = listOf(
        listOf(
            "20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070",
            "지방 집주인 서울 아파트 산다",
            "놀아도 월 198만 원 받는데…",
            "골프 대신 해외주식? 새로운 트렌드"
        ),
        listOf(
            "청년 투자 심리 위축",
            "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            "연금 개편안, 당신의 은퇴는?",
            "전세 사기 피해자들 구제책은?"
        ),
        listOf(
            "청년 투자 심리 위축",
            "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            "연금 개편안, 당신의 은퇴는?",
            "전세 사기 피해자들 구제책은?"
        ),
        listOf(
            "청년 투자 심리 위축",
            "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            "연금 개편안, 당신의 은퇴는?",
            "전세 사기 피해자들 구제책은?"
        ),
        listOf(
            "청년 투자 심리 위축",
            "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            "연금 개편안, 당신의 은퇴는?",
            "전세 사기 피해자들 구제책은?"
        ),
        listOf(
            "청년 투자 심리 위축",
            "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
            "연금 개편안, 당신의 은퇴는?",
            "전세 사기 피해자들 구제책은?"
        )
    )*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MoneyMateTheme.colors.backgroundWhite)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(MoneyMateTheme.colors.white)
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
        Spacer(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth())

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(dummyNewsData) { articles ->
                NewsContainer(
                    news = articles,
                    onAddClick = {
                        onAddClick(articles.publisher)
                    },
                    onArticleClick = { }
                )
            }
        }

    }
}
