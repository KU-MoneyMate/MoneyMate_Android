package com.moneymate.moneymate.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.moneymate.moneymate.ui.theme.MoneyMateTheme.colors
import com.moneymate.moneymate.ui.theme.MoneyMateTheme.typography

object MoneyMateTheme {
    val colors: MoneyMateColors
        @Composable
        @ReadOnlyComposable
        get() = LocalMoneyMateColorsProvider.current

    val typography: MoneyMateTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalMoneyMateTypographyProvider.current
}

@Composable
fun MoneyMateTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMoneyMateColorsProvider provides colors,
        LocalMoneyMateTypographyProvider provides typography
    ) {
        MaterialTheme(
            content = content
        )
    }
}
