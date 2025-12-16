package com.moneymate.moneymate.data.dto.finance.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DepositProductResponse(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("data") val data: List<DepositProductItemDto>
)

@Serializable
data class DepositProductItemDto(
    @SerialName("bankName") val bankName: String? = null,
    @SerialName("productName") val productName: String? = null,
    @SerialName("joinWay") val joinWay: String? = null,
    @SerialName("dclsStrtDay") val dclsStrtDay: String? = null,
    @SerialName("dclsEndDay") val dclsEndDay: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("callNum") val callNum: String? = null,
    @SerialName("intrRate") val intrRate: String? = null,
    @SerialName("maxIntrRate") val maxIntrRate: String? = null,
    @SerialName("intrType") val intrType: String? = null,
    @SerialName("mtrtInt") val mtrtInt: String? = null,
    @SerialName("spclCnd") val spclCnd: String? = null,
    @SerialName("joinDeny") val joinDeny: String? = null,
    @SerialName("joinMember") val joinMember: String? = null,
    @SerialName("etcNote") val etcNote: String? = null,
    @SerialName("maxLimit") val maxLimit: String? = null
)

//형식 바꾸기
data class DepositProduct(
    val bankName: String,
    val productName: String,
    val joinWayList: List<String>,
    val dclsStrtDay: String?,
    val dclsEndDay: String?,
    val url: String?,
    val callNum: String?,
    val intrRate: Double?,
    val maxIntrRate: Double?,
    val intrType: String?,
    val mtrtInt: String?,
    val spclCnd: String?,
    val joinDeny: String?,
    val joinMember: String?,
    val etcNote: String?,
    val maxLimit: Long?
)

private fun String?.safeToDouble(): Double? =
    this?.trim()?.takeIf { it.isNotEmpty() }?.toDoubleOrNull()

private fun String?.safeToLong(): Long? =
    this?.trim()?.takeIf { it.isNotEmpty() }?.toLongOrNull()

fun DepositProductItemDto.toDomain(): DepositProduct =
    DepositProduct(
        bankName = bankName.orEmpty(),
        productName = productName.orEmpty(),
        joinWayList = joinWay?.split(",")?.map { it.trim() }?.filter { it.isNotEmpty() } ?: emptyList(),
        dclsStrtDay = dclsStrtDay,
        dclsEndDay = dclsEndDay,
        url = url,
        callNum = callNum,
        intrRate = intrRate.safeToDouble(),
        maxIntrRate = maxIntrRate.safeToDouble(),
        intrType = intrType,
        mtrtInt = mtrtInt,
        spclCnd = spclCnd,
        joinDeny = joinDeny,
        joinMember = joinMember,
        etcNote = etcNote,
        maxLimit = maxLimit.safeToLong()
    )