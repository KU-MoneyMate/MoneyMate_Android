package com.moneymate.moneymate.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.moneymate.moneymate.BuildConfig
import com.moneymate.moneymate.util.auth.AuthInterceptor
import com.moneymate.moneymate.util.auth.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesConverterFactory(): Converter.Factory =
        Json.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun providesDefaultOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(authInterceptor)
            authenticator(tokenAuthenticator)
        }.build()

    @Provides
    @Singleton
    @ForeignStockOkHttpClient
    fun providesStockOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

    @Provides
    @Singleton
    @DomesticStockOkHttpClient
    fun providesDomesticStockOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }.build()

    @Provides
    @Singleton
    @DefaultRetrofit
    fun providesDefaultRetrofit(
        client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()

    @Provides
    @Singleton
    @ForeignStockRetrofit
    fun providesForeignStockRetrofit(
        @ForeignStockOkHttpClient client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.FOREIGN_STOCK_BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()


    @Provides
    @Singleton
    @DomesticStockRetrofit
    fun providesDomesticStockRetrofit(
        @DomesticStockOkHttpClient client: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.DOMESTIC_STOCK_BASE_URL)
        .client(client)
        .addConverterFactory(converterFactory)
        .build()
}