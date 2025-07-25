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
    val kakaoOptions: String,
    @SerialName("naverOptions")
    val naverOptions: String,
    @SerialName("rcsOptions")
    val rcsOptions: String,
    @SerialName("type")
    val type: String,
    @SerialName("country")
    val country: String,
    @SerialName("subject")
    val subject: String,
    @SerialName("imageId")
    val imageId: String,
    @SerialName("dateProcessed")
    val dateProcessed: String,
    @SerialName("dateReported")
    val dateReported: String,
    @SerialName("dateReceived")
    val dateReceived: String,
    @SerialName("statusCode")
    val statusCode: String,
    @SerialName("replacement")
    val replacement: String,
    @SerialName("autoTypeDetect")
    val autoTypeDetect: String,
    @SerialName("status")
    val status: String,
    @SerialName("messageId")
    val messageId: String,
    @SerialName("groupId")
    val groupId: String,
    @SerialName("accountId")
    val accountId: String,
    val text: String,
    @SerialName("dateCreated")
    val dateCreated: String,
    @SerialName("dateUpdated")
    val dateUpdated: String,
    @SerialName("to")
    val to: String, // 수신자
    @SerialName("from")
    val from: String, // 발신자
    @SerialName("customFields")
    val customFields: String,
    @SerialName("replacements")
    val replacements:String
)