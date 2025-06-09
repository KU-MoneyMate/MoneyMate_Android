package com.moneymate.moneymate.data.dto.mypage.request

import kotlinx.serialization.Serializable

@Serializable
data class RetireInputRequest(
    val age: Int,
    val retireAge: Int,
    val currentAssets: Long,
    val annualIncome: Long,
    val annualExpense: Long,
    val pensionPerYear: Long,
    val accounts: Long,
    val realEstate: Long,
    val stocks: Long,
    val endAge: Int,
    val assetReturnRate: Double,
    val incomeGrowthRate: Double,
    val inflationRate: Double,
    val pensionStartAge: Int,
    val consumptionDropAge: Int,
    val consumptionDropRate: Double,
    val crashCycle: Int,
    val crashImpactRate: Double

)
