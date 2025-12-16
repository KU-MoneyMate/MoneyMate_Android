package com.moneymate.moneymate.data.dto.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhoneVerificationCodeResponse(
    val status: String,
    val message: String,
    val data: VerificationData
)

@Serializable
data class VerificationData(
    val kakaoOptions: String? = null,
    @SerialName("naverOptions")
    val naverOptions: String? = null,
    @SerialName("rcsOptions")
    val rcsOptions: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("country")
    val country: String? = null,
    @SerialName("subject")
    val subject: String? = null,
    @SerialName("imageId")
    val imageId: String? = null,
    @SerialName("dateProcessed")
    val dateProcessed: String? = null,
    @SerialName("dateReported")
    val dateReported: String? = null,
    @SerialName("dateReceived")
    val dateReceived: String? = null,
    @SerialName("statusCode")
    val statusCode: String? = null,
    @SerialName("replacement")
    val replacement: String? = null,
    @SerialName("autoTypeDetect")
    val autoTypeDetect: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("messageId")
    val messageId: String? = null,
    @SerialName("groupId")
    val groupId: String? = null,
    @SerialName("accountId")
    val accountId: String? = null,
    val text: String,
    @SerialName("dateCreated")
    val dateCreated: String? = null,
    @SerialName("dateUpdated")
    val dateUpdated: String? = null,
    @SerialName("to")
    val to: String,
    @SerialName("from")
    val from: String,
    @SerialName("customFields")
    val customFields: String? = null,
    @SerialName("replacements")
    val replacements: String? = null
)