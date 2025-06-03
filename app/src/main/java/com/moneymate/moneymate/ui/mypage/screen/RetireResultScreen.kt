package com.moneymate.moneymate.ui.mypage.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun RetireResultScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val mockData = listOf(
        RetireResultRowData(30, "10,000", "4,000", "6,000"),
        RetireResultRowData(31, "10,000", "4,000", "6,000"),
        RetireResultRowData(32, "10,000", "4,000", "6,000"),
        RetireResultRowData(33, "10,000", "4,000", "6,000"),
        RetireResultRowData(34, "10,000", "4,000", "6,000"),
        RetireResultRowData(35, "10,000", "4,000", "6,000")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_mypage_arrow),
                        contentDescription = "뒤로가기",
                        modifier = Modifier.rotate(180f)
                    )
                }
                Box(
                    modifier = Modifier.weight(2f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "노후 설계 시뮬레이션",
                        color = MoneyMateTheme.colors.darkGray,
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            fontSize = 20.sp
                        )
                    )
                }
                Box(modifier = Modifier.weight(1f))
            }

            Text(
                text = "입력된 정보를 바탕으로 조회한\n사용자의 노후 설계 시뮬레이션 결과입니다.\n(단위 : 만원)",
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp),
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    fontSize = 16.sp
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableHeader("나이")
                TableHeader("자산")
                TableHeader("연 소비")
                TableHeader("연 수입")
            }

            Divider(color = Color.Gray.copy(alpha = 0.4f), thickness = 1.dp)
        }

        items(mockData) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(row.age.toString())
                TableCell(row.asset)
                TableCell(row.spending)
                TableCell(row.income)
            }
        }
    }
}

@Composable
fun TableHeader(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun TableCell(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    )
}
