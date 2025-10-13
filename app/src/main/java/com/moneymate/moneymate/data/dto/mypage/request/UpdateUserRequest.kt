package com.moneymate.moneymate.data.dto.mypage.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    @SerialName("userId")
    val userId: String,

    @SerialName("userName")
    val userName: String,

    @SerialName("password")
    val password: String? = null,

    @SerialName("phoneNumber")
    val phoneNumber: String,

    @SerialName("birthday")
    val birthday: String
)