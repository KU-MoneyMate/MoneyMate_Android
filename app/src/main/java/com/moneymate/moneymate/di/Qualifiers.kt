package com.moneymate.moneymate.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ForeignStockRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ForeignStockOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DomesticStockRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DomesticStockOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InsightRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InsightOkHttpClient