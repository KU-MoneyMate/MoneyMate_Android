package com.moneymate.moneymate.ui.insight.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NewsSummaryItem(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    generatedTime: String
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title
        )
        Text(
            text = content
        )
    }
}