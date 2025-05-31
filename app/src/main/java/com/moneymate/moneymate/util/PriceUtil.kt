package com.moneymate.moneymate.util

import java.text.DecimalFormat

fun Int.toDecimalFormat() : String {
    val decimalFormat = DecimalFormat("#,###")
    return decimalFormat.format(this)
}
