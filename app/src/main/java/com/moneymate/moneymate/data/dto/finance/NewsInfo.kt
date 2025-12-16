package com.moneymate.moneymate.data.dto.finance

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsInfo(
    @SerialName("title")
    val title: String,
    @SerialName("link")
    val link: String,
    @SerialName("description")
    val description: String,
    @SerialName("publisher")
    val publisher: String,
    @SerialName("category")
    val category: String,
    @SerialName("pubDate")
    val pubDate: String,
    @SerialName("author")
    val author: String,
)