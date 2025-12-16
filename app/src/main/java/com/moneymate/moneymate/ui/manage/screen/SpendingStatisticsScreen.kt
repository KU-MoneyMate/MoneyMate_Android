import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.manage.component.SpendingStatisticsItem
import com.moneymate.moneymate.ui.theme.MoneyMateTheme
import java.time.LocalDate
import kotlin.math.round

@Composable
fun SpendingStatisticsScreen(
    modifier: Modifier,
    viewModel: ManageViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
){

    var currentMonth by remember { mutableStateOf(LocalDate.now()) }
    LaunchedEffect(currentMonth) {
        viewModel.getSpendingStatistics(currentMonth)
    }

    val spendingData = viewModel.spendingStat.collectAsState().value
    val totalAmount = spendingData?.categoryTotals?.values?.sum() ?: 0L

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MoneyMateTheme.colors.white)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
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
                        .clickable { onNavigateBack() }
                )
            }
            Box(
                modifier = Modifier.weight(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "소비 통계",
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
            text = "소비 통계 조회입니다.\n원하는 옵션을 선택해서 소비 현황을 조회해보세요.",
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 24.dp),
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                fontSize = 16.sp
            )
        )

        //월 선택
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "previous month",
                modifier = Modifier.clickable {
                    currentMonth = currentMonth.minusMonths(1)
                }
            )
            Text(
                text = "${currentMonth.year}년 ${currentMonth.monthValue}월",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 20.sp
                )
            )
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = "next month",
                modifier = Modifier
                    .rotate(180f)
                    .clickable {
                        currentMonth = currentMonth.plusMonths(1)
                    }
            )
        }

        //그래프에 들어갈 데이터 내림차순 정렬
        val chartCategories = spendingData
            ?.categoryTotals
            ?.filter { it.value > 0 } // 0원 항목 제외
            ?.entries
            ?.sortedByDescending { it.value }
            ?.map { CategoryAmount(it.key, it.value.toFloat()) }
            ?: emptyList()

        //도넛차트
        SpendingStatisticChart(
            totalAmount = totalAmount.toFloat(),
            categories = chartCategories
        )

        //퍼센테이지 계산 (둘째자리에서 반올림)
        val itemList = spendingData
            ?.categoryTotals
            ?.filter { it.value > 0 }
            ?.entries
            ?.sortedByDescending { it.value }
            ?.mapIndexed { index, entry ->
                val raw = if (totalAmount > 0) {
                    entry.value.toDouble() * 100.0 / totalAmount.toDouble()
                } else 0.0
                val percent = round(raw * 10) / 10.0
                Triple(index, entry.key, Pair(percent, entry.value.toInt()))
            }
            ?: emptyList()

        //카테고리별 소비 금액 표시
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.fillMaxSize()
        ) {
            items(itemList) { (index, name, pair) ->
                val (percent, amount) = pair
                SpendingStatisticsItem(index, name, percent, amount)
            }
        }
    }
}

