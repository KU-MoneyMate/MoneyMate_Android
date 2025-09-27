package com.moneymate.moneymate.data.dto.finance.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MortgageLoanProductResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("data") val data: List<MortgageLoanProductItemDto>
)

@Serializable
data class MortgageLoanProductItemDto(
    @SerialName("bankName")         val bankName: String? = null,
    @SerialName("productName")      val productName: String? = null,
    @SerialName("joinWay")          val joinWay: String? = null,
    @SerialName("lendRateType")     val lendRateType: String? = null,
    @SerialName("lendRateMin")      val lendRateMin: String? = null,
    @SerialName("lendRateMax")      val lendRateMax: String? = null,
    @SerialName("lendRateAvg")      val lendRateAvg: String? = null,
    @SerialName("loanInciExpn")     val loanInciExpn: String? = null,
    @SerialName("erlyRpayFee")      val erlyRpayFee: String? = null,
    @SerialName("dlyRate")          val dlyRate: String? = null,
    @SerialName("loanLmt")          val loanLmt: String? = null,
    @SerialName("mrtgType")         val mrtgType: String? = null,
    @SerialName("rpayType")         val rpayType: String? = null,
    @SerialName("dclsStrtDay")      val dclsStrtDay: String? = null,
    @SerialName("dclsEndDay")       val dclsEndDay: String? = null,
    @SerialName("url")              val url: String? = null,
    @SerialName("callNum")          val callNum: String? = null
)