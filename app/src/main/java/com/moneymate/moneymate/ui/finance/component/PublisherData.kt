package com.moneymate.moneymate.ui.finance.component

data class PublisherData(
    val enum : String,
    val publisherName : String,
    val introduction : String,
    val imageUrl : String,
    val category: List<CategoryData>,
)

data class CategoryData(
    val categoryName : String,
    val isSelected : Boolean,
)
