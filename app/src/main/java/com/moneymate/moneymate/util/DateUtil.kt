package com.moneymate.moneymate.util

// "2025-05-11" -> "5월 11일")
fun formatDate(date: String): String {
    return "${date.substring(5, 7).toInt()}월 ${date.substring(8, 10).toInt()}일"
}
