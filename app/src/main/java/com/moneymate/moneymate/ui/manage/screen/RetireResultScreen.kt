package com.moneymate.moneymate.ui.manage.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHostController
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.navigation.Route
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@SuppressLint("UnrememberedGetBackStackEntry")
@Composable
fun RetireResultScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ManageViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val parentEntry = remember { navController.getBackStackEntry(Route.RetireGraph.route) }
    val viewModel: ManageViewModel = hiltViewModel(parentEntry)

    val result = viewModel.retireResult.collectAsState().value

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
                        modifier = Modifier
                            .rotate(180f)
                            .clickable {
                                viewModel.clearRetireResult()
                                onNavigateBack()
                            }
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
                    .padding(vertical = 8.dp)
            ) {
                TableHeader("나이", modifier=Modifier.weight(1f))
                TableHeader("자산", modifier=Modifier.weight(4f))
                TableHeader("연 소비", modifier=Modifier.weight(2.5f))
                TableHeader("연 수입", modifier=Modifier.weight(2.5f))
            }

            Divider(color = Color.Gray.copy(alpha = 0.4f), thickness = 1.dp)
        }

        items(result.orEmpty()) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                TableCell("${row.age}", modifier=Modifier.weight(1f))
                TableCell("${row.asset / 10_000}", modifier=Modifier.weight(4f))
                TableCell("${row.expense / 10_000}", modifier=Modifier.weight(2.5f))
                TableCell("${row.income / 10_000}", modifier=Modifier.weight(2.5f))
            }
        }
    }
}

@Composable
fun TableHeader(text: String, modifier:Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
fun TableCell(text: String, modifier: Modifier=Modifier) {
    Text(
        text = text,
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}
