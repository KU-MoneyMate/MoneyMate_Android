package com.moneymate.moneymate.util.auth

fun String.isValidId(): Boolean {
    // 영문 8자 이상
    val idPattern = "^[a-zA-Z]{8,}$".toRegex()
    return this.matches(idPattern)
}

fun String.isValidPassword(): Boolean {
    // 영문, 숫자 포함 8자 이상
    val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$".toRegex()
    return this.matches(passwordPattern)
}

