package com.moneymate.moneymate.ui.finance.screen

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
import com.moneymate.moneymate.ui.finance.component.NewsCategoryButton
import com.moneymate.moneymate.ui.finance.component.NewsContainer
import com.moneymate.moneymate.ui.finance.component.NewsItem
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsPublisherHomeScreen(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: FinanceViewModel = hiltViewModel(),
    onNavigateBack: () -> Boolean,
) {
    val dummyArticle = listOf(
        "20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070",
        "지방 집주인 서울 아파트 산다",
        "놀아도 월 198만 원 받는데…",
        "골프 대신 해외주식? 새로운 트렌드",
        "청년 투자 심리 위축",
        "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
        "연금 개편안, 당신의 은퇴는?",
        "전세 사기 피해자들 구제책은?",
        "20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070",
        "지방 집주인 서울 아파트 산다",
        "놀아도 월 198만 원 받는데…",
        "골프 대신 해외주식? 새로운 트렌드",
        "청년 투자 심리 위축",
        "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
        "연금 개편안, 당신의 은퇴는?",
        "전세 사기 피해자들 구제책은?",
        "20년 살던 집 팔아 수십억 벌었다…강남 떠나는 5070",
        "지방 집주인 서울 아파트 산다",
        "놀아도 월 198만 원 받는데…",
        "골프 대신 해외주식? 새로운 트렌드",
        "청년 투자 심리 위축",
        "부동산 시장 급변, 어떻게 대응할까ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ",
        "연금 개편안, 당신의 은퇴는?",
        "전세 사기 피해자들 구제책은?"
    )

    val scrollState = rememberScrollState()

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
            Text(
                text = "한국경제",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    fontSize = 32.sp,
                    color = MoneyMateTheme.colors.black
                )
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "성공을 부르는 습관",
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
                .background(MoneyMateTheme.colors.white)
        ) {
            Spacer(Modifier.width(33.dp))
            Row (
                modifier = Modifier
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NewsCategoryButton("경제", true) { }
                NewsCategoryButton("증권", false) { }
                NewsCategoryButton("금융", false) { }
                NewsCategoryButton("부동산", false) { }
            }
            Spacer(Modifier.width(33.dp))
        }

        Spacer(modifier = Modifier.height(17.dp))

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .weight(1f)
        ) {
            items(dummyArticle.size) { index ->
                val article = dummyArticle[index]
                val isLast = index == dummyArticle.lastIndex

                NewsItem(
                    title = article,
                    url = "",
                    isLastArticle = isLast,
                    onClick = {
                        // TODO
                    }
                )
            }
        }

    }
}