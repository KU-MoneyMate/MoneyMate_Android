package com.kuit.moneymate.ui.asset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kuit.moneymate.ui.theme.MoneyMateTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "HomeScreen",
            style = MoneyMateTheme.typography.head_02_B_20,
            color = MoneyMateTheme.colors.black
        )
        Spacer(modifier = Modifier.size(10.dp))
    }
}