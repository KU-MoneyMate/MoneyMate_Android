package com.moneymate.moneymate.di

import com.moneymate.moneymate.data.repository.AssetRepository
import com.moneymate.moneymate.data.repository.AuthRepository
import com.moneymate.moneymate.data.repository.FinanceRepository
import com.moneymate.moneymate.data.repository.ManageRepository
import com.moneymate.moneymate.data.service.AssetService
import com.moneymate.moneymate.data.service.AuthService
import com.moneymate.moneymate.data.service.FinanceService
import com.moneymate.moneymate.data.service.ManageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesAuthRepository(authService: AuthService): AuthRepository = AuthRepository(authService)

    @Provides
    @Singleton
    fun providesAssetRepository(assetService: AssetService): AssetRepository = AssetRepository(assetService)

    @Provides
    @Singleton
    fun providesFinanceRepository(financeService: FinanceService): FinanceRepository = FinanceRepository(financeService)

    @Provides
    @Singleton
    fun providesManageRepository(manageService: ManageService): ManageRepository = ManageRepository(manageService)
}