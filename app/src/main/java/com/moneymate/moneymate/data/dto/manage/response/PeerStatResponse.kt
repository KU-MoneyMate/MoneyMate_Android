package com.moneymate.moneymate.data.dto.manage.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PeerStatResponse(
    @SerialName("status")
    val status: String,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Data>
) {
    @Serializable
    data class Data(
        @SerialName("age")
        val age: Int,
        @SerialName("c2")
        val c2: String,
        @SerialName("item")
        val item: String,
        @SerialName("itemName")
        val itemName: String,
        @SerialName("year")
        val year: String,
        @SerialName("unitName")
        val unitName: String,
        @SerialName("value")
        val value: Double?
    )
}