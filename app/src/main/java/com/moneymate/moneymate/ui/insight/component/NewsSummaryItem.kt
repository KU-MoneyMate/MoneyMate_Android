package com.moneymate.moneymate.ui.insight.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichTextStyle
import com.halilibo.richtext.ui.material3.Material3RichText
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsSummaryItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    generatedTime: String
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = when (title) {
                    "stock" -> "증시 뉴스 요약"
                    "realestate" -> "부동산 뉴스 요약"
                    "economy" -> "경제 뉴스 요약"
                    else -> "증시 뉴스 요약"
                },
                style = MoneyMateTheme.typography.head_01_B_24
                    .copy(color = MoneyMateTheme.colors.deepBlue)
            )
            Text(
                text = generatedTime.substringBefore("T"),
                style = MoneyMateTheme.typography.body_01_M_14
                    .copy(color = MoneyMateTheme.colors.lightGray)
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            thickness = 1.dp,
            color = MoneyMateTheme.colors.deepBlue
        )
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
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
                    Markdown(content = content)
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsSummaryItemPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NewsSummaryItem(
            modifier = Modifier,
            title = "마크다운 예시",
            content = "# Heading1\n" +
                    "## Heading2\n" +
                    "### Heading3\n" +
                    "**bold**\n" +
                    "*italic*\n" +
                    "~~strike~~\n" +
                    "> quote\n" +
                    "\n" +
                    "- list1\n" +
                    "- list2\n" +
                    "\n" +
                    "[link](https://www.naver.com)\n" +
                    "\n" +
                    "```kotlin\n" +
                    "val a = 10\n" +
                    "println(a)\n" +
                    "```\n",
            generatedTime = ""
        )
        Spacer(modifier = Modifier.size(20.dp))
        NewsSummaryItem(
            modifier = Modifier,
            title = "제목",
            content = "서울 일부 비규제지역(성동, 마포, 분당, 과천 등)을 중심으로 정부의 추가 규제 발표 전 '막차'를 타려는 매수 심리가 확산하며 집값이 급등하고 매물이 감소하고 있다.\n\n이에 정부는 과열 양상을 보이는 지역을 규제지역으로 추가 지정하고, 주택담보대출 한도 축소 및 DSR 규제 강화 등을 포함한 추가 부동산 대책 발표를 예고했다. 다만, 강력한 대출 규제가 매매 수요를 전세 시장으로 이동시켜 전셋값 상승을 부추길 수 있다는 우려도 제기된다.\n\n실제로 서울은 대출 규제, 집주인의 실거주 증가, 기존 세입자의 계약 갱신 등으로 전세 매물 부족 현상이 심화되고 있다. 이는 전셋값 급등과 월세화 가속으로 이어지고 있다.\n\n공급 측면에서는 서울 잠원동 일대 소규모 단지들이 관리처분인가를 받는 등 정비사업이 속도를 내고 있으나, 높은 공사비로 인한 조합원 분담금 부담이 과제로 남아있다. 서울시는 신속통합기획을 통해 은마아파트 등 주요 단지의 재건축을 지원하며 공급 확대를 꾀하고 있다. 반면 지방은 신규 아파트 공급이 급감했다.\n\n이외에 수도권에 집중된 집값 담합 시도에 대한 당국의 조사가 진행 중이며, 부동산 정책을 둘러싼 정치권의 책임 공방과 아파트 단지 간 갈등도 나타나고 있다.",
            generatedTime = "2025.11.11"
        )
    }
}