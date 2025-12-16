package com.moneymate.moneymate.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

// "2025-05-11" -> "5월 11일")
fun formatDate(date: String): String {
    return "${date.substring(5, 7).toInt()}월 ${date.substring(8, 10).toInt()}일"
}

// yyyy-MM-dd 포맷
val API_DATE_FMT: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

// 해당 월의 1일 / 말일
fun LocalDate.startOfMonth(): LocalDate = withDayOfMonth(1)
fun LocalDate.endOfMonth(): LocalDate = withDayOfMonth(lengthOfMonth())
