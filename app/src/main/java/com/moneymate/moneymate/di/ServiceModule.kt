package com.moneymate.moneymate.di

import com.moneymate.moneymate.data.service.AssetService
import com.moneymate.moneymate.data.service.AuthService
import com.moneymate.moneymate.data.service.FinanceService
import com.moneymate.moneymate.data.service.ManageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesAssetService(retrofit: Retrofit): AssetService =
        retrofit.create(AssetService::class.java)

    @Provides
    @Singleton
    fun providesFinanceService(retrofit: Retrofit): FinanceService =
        retrofit.create(FinanceService::class.java)

    @Provides
    @Singleton
    fun providesManageService(retrofit: Retrofit): ManageService =
        retrofit.create(ManageService::class.java)
}