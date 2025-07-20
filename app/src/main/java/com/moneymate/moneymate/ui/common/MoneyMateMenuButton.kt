package com.moneymate.moneymate.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun MoneyMateMenuButton(
    text: String,
    height: Int,
    onClick : () ->Unit
) {
    Card(
        modifier = Modifier
            .height(height.dp)
            .width(360.dp)
            .clickable {onClick()},
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MoneyMateTheme.colors.white
        )

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 29.dp, end = 21.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = MoneyMateTheme.colors.darkGray,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    fontSize = 20.sp
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_mypage_arrow),
                contentDescription = "화살표 아이콘",
                tint = Color.Gray
            )
        }
    }
}