package com.moneymate.moneymate.ui.manage.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun SpendingStatisticsItem(
    categoryRank : Int,
    categoryName : String,
    percentage : Double,
    expense : Int
){
    var rankIndex = categoryRank

    if (categoryRank > 7) { rankIndex = 7 }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(81.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .padding(start = 25.dp)
                .size(24.dp)
                .background(donutColors[rankIndex]) ,
        )
        Column(
            modifier = Modifier.padding(start = 15.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = categoryName,
                style = MoneyMateTheme.typography.head_02_B_20
            )
            Spacer(modifier=Modifier.height(7.dp))
            Text (
                text = percentage.toString()+"%",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                    fontSize = 16.sp
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column (
            modifier = Modifier
                .padding(end = 37.dp),
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = expense.toString()+"원",
                style = MoneyMateTheme.typography.head_02_B_20
            )
        }
    }
}

private val donutColors = listOf(
    Color(0xFF0E0857),
    Color(0xFF1C1970),
    Color(0xFF2A2D89),
    Color(0xFF3B44A2),
    Color(0xFF5561BB),
    Color(0xFF7582D4),
    Color(0xFF9DA9ED),
    Color(0xFFF5F5F5),
)