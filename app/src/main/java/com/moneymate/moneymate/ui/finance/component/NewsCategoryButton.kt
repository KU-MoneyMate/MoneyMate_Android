package com.moneymate.moneymate.ui.finance.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R
import com.moneymate.moneymate.ui.theme.MoneyMateTheme

@Composable
fun NewsCategoryButton(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(42.dp)
            .width(91.dp)
            .border(2.dp, MoneyMateTheme.colors.deepBlue, RoundedCornerShape(25.dp))
            .background(
                color = if (isSelected) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = category,
            style = TextStyle(
                color = if (isSelected) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NewsCategoryButtonPreview(
    modifier: Modifier = Modifier,
    category: String = "경제",
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .height(42.dp)
            .width(91.dp)
            .border(2.dp, MoneyMateTheme.colors.deepBlue, RoundedCornerShape(25.dp))
            .background(
                color = if (isSelected) MoneyMateTheme.colors.deepBlue else MoneyMateTheme.colors.white,
                shape = RoundedCornerShape(25.dp)
            )
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = category,
            style = TextStyle(
                color = if (isSelected) MoneyMateTheme.colors.white else MoneyMateTheme.colors.deepBlue,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold))
            )
        )
    }
}
