package com.moneymate.moneymate.data.dto.finance.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditLoanProductResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("data") val data: List<CreditLoanProductItemDto>
)

@Serializable
data class CreditLoanProductItemDto(
    @SerialName("bankName")         val bankName: String? = null,
    @SerialName("productName")      val productName: String? = null,
    @SerialName("joinWay")          val joinWay: String? = null,
    @SerialName("crdtPrdtType")     val crdtPrdtType: String? = null,
    @SerialName("crdtLendRateType") val crdtLendRateType: String? = null,
    @SerialName("crdtGrad9")        val crdtGrad9: String? = null,
    @SerialName("crdtGrad98")       val crdtGrad98: String? = null,
    @SerialName("crdtGrad87")       val crdtGrad87: String? = null,
    @SerialName("crdtGrad76")       val crdtGrad76: String? = null,
    @SerialName("crdtGrad65")       val crdtGrad65: String? = null,
    @SerialName("crdtGrad54")       val crdtGrad54: String? = null,
    @SerialName("crdtGrad43")       val crdtGrad43: String? = null,
    @SerialName("crdtGrad3")        val crdtGrad3: String? = null,
    @SerialName("cbName")           val cbName: String? = null,
    @SerialName("dclsStrtDay")      val dclsStrtDay: String? = null,
    @SerialName("dclsEndDay")       val dclsEndDay: String? = null,
    @SerialName("url")              val url: String? = null,
    @SerialName("callNum")          val callNum: String? = null
)
