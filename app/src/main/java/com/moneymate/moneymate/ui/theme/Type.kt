package com.moneymate.moneymate.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.moneymate.moneymate.R

val moneyMateFontBold = FontFamily(Font(R.font.pretendard_bold))
val moneyMateFontSemiBold = FontFamily(Font(R.font.pretendard_semibold))
val moneyMateFontMedium = FontFamily(Font(R.font.pretendard_medium))
val moneyMateFontRegular = FontFamily(Font(R.font.pretendard_regular))

@Immutable
data class MoneyMateTypography(
    // Head
    val head_01_B_24: TextStyle,
    val head_02_B_20: TextStyle,
    val head_03_B_16: TextStyle,
    val head_03_SB_16: TextStyle,
    val head_03_R_16: TextStyle,
    val head_04_SB_14: TextStyle,
    val head_05_B_10: TextStyle,

    // Body
    val body_01_M_14: TextStyle,
    val body_01_M_16: TextStyle,
    val body_01_R_14: TextStyle,
    val body_01_R_16: TextStyle,
    val body_02_SB_12: TextStyle,
    val body_02_R_12: TextStyle,
    val body_03_M_20: TextStyle,

    // Caption
    val caption_01_R_10: TextStyle
)

val defaultMoneyMateTypography = MoneyMateTypography(
    // Head
    head_01_B_24 = TextStyle(
        fontFamily = moneyMateFontBold,
        fontSize = 24.sp,
        lineHeight = 24.sp
    ),
    head_02_B_20 = TextStyle(
        fontFamily = moneyMateFontBold,
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),
    head_03_B_16 = TextStyle(
        fontFamily = moneyMateFontBold,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    head_03_SB_16 = TextStyle(
        fontFamily = moneyMateFontSemiBold,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    head_03_R_16 = TextStyle(
        fontFamily = moneyMateFontRegular,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    head_04_SB_14 = TextStyle(
        fontFamily = moneyMateFontSemiBold,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    head_05_B_10 = TextStyle(
        fontFamily = moneyMateFontBold,
        fontSize = 10.sp,
        lineHeight = 10.sp
    ),

    // Body
    body_01_M_14 = TextStyle(
        fontFamily = moneyMateFontMedium,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    body_01_M_16 = TextStyle(
        fontFamily = moneyMateFontMedium,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    body_01_R_14 = TextStyle(
        fontFamily = moneyMateFontRegular,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    body_01_R_16 = TextStyle(
        fontFamily = moneyMateFontRegular,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    body_02_SB_12 = TextStyle(
        fontFamily = moneyMateFontSemiBold,
        fontSize = 12.sp,
        lineHeight = 12.sp
    ),
    body_02_R_12 = TextStyle(
        fontFamily = moneyMateFontRegular,
        fontSize = 12.sp,
        lineHeight = 12.sp
    ),
    body_03_M_20 = TextStyle(
        fontFamily = moneyMateFontMedium,
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),

    // Caption
    caption_01_R_10 = TextStyle(
        fontFamily = moneyMateFontRegular,
        fontSize = 10.sp,
        lineHeight = 10.sp
    )
)

val LocalMoneyMateTypographyProvider = staticCompositionLocalOf { defaultMoneyMateTypography }
