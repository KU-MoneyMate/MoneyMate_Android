package com.moneymate.moneymate.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val BackgroundWhite = Color(0xFFF5F5F5)
val DarkGray = Color(0xFF333333)
val LightGray = Color(0x99333333)

val Neutral100 = Color(0xFFF7F7F9)
val Neutral300 = Color(0xFFE5E5E7)
val Neutral500 = Color(0xFFA4A4A6)

val DeepBlue = Color(0xFF0E0857)

@Immutable
data class MoneyMateColors(
    val white: Color,
    val black: Color,
    val backgroundWhite: Color,
    val darkGray: Color,
    val lightGray: Color,
    val neutral100: Color,
    val neutral300: Color,
    val neutral500: Color,
    val deepBlue: Color,
)

val defaultMoneyMateColors = MoneyMateColors(
    white = White,
    black = Black,
    backgroundWhite = BackgroundWhite,
    darkGray = DarkGray,
    lightGray = LightGray,
    neutral100 = Neutral100,
    neutral300 = Neutral300,
    neutral500 = Neutral500,
    deepBlue = DeepBlue,
)

val LocalMoneyMateColorsProvider = staticCompositionLocalOf { defaultMoneyMateColors }
