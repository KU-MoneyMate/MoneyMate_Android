package com.moneymate.moneymate.di

import com.moneymate.moneymate.data.service.InsightService
import com.moneymate.moneymate.data.service.AssetService
import com.moneymate.moneymate.data.service.AuthService
import com.moneymate.moneymate.data.service.FinanceService
import com.moneymate.moneymate.data.service.ForeignStockService
import com.moneymate.moneymate.data.service.DomesticStockService
import com.moneymate.moneymate.data.service.ManageService
import com.moneymate.moneymate.data.service.MyPageService
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
    fun providesAuthService(@DefaultRetrofit retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesAssetService(@DefaultRetrofit retrofit: Retrofit): AssetService =
        retrofit.create(AssetService::class.java)

    @Provides
    @Singleton
    fun providesFinanceService(@DefaultRetrofit retrofit: Retrofit): FinanceService =
        retrofit.create(FinanceService::class.java)

    @Provides
    @Singleton
    fun providesManageService(@DefaultRetrofit retrofit: Retrofit): ManageService =
        retrofit.create(ManageService::class.java)

    @Provides
    @Singleton
    fun providesForeignStockService(@ForeignStockRetrofit retrofit: Retrofit): ForeignStockService =
        retrofit.create(ForeignStockService::class.java)

    @Provides
    @Singleton
    fun providesDomesticStockService(@DomesticStockRetrofit retrofit: Retrofit): DomesticStockService =
        retrofit.create(DomesticStockService::class.java)

    @Provides
    @Singleton
    fun providesMyPageService(@DefaultRetrofit retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun providesInsightService(@InsightRetrofit retrofit: Retrofit): InsightService =
        retrofit.create(InsightService::class.java)
}