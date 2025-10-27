package com.moneymate.moneymate.di

import com.moneymate.moneymate.data.repository.AssetRepository
import com.moneymate.moneymate.data.repository.AuthRepository
import com.moneymate.moneymate.data.repository.FinanceRepository
import com.moneymate.moneymate.data.repository.ManageRepository
import com.moneymate.moneymate.data.repository.ForeignStockRepository
import com.moneymate.moneymate.data.repository.DomesticStockRepository
import com.moneymate.moneymate.data.repository.StockIconRepository
import com.moneymate.moneymate.data.service.AssetService
import com.moneymate.moneymate.data.service.AuthService
import com.moneymate.moneymate.data.service.FinanceService
import com.moneymate.moneymate.data.service.ManageService
import com.moneymate.moneymate.data.service.ForeignStockService
import com.moneymate.moneymate.data.service.DomesticStockService
import com.moneymate.moneymate.data.service.StockIconService
import com.moneymate.moneymate.util.auth.TokenManager
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
    fun providesAuthRepository(
        authService: AuthService,
        tokenManager: TokenManager
    ): AuthRepository = AuthRepository(authService, tokenManager)

    @Provides
    @Singleton
    fun providesAssetRepository(assetService: AssetService): AssetRepository = AssetRepository(assetService)

    @Provides
    @Singleton
    fun providesFinanceRepository(financeService: FinanceService): FinanceRepository = FinanceRepository(financeService)

    @Provides
    @Singleton
    fun providesManageRepository(manageService: ManageService): ManageRepository = ManageRepository(manageService)

    @Provides
    @Singleton
    fun providesForeignStockRepository(foreignStockService: ForeignStockService): ForeignStockRepository = ForeignStockRepository(foreignStockService)

    @Provides
    @Singleton
    fun providesDomesticStockRepository(domesticStockService: DomesticStockService): DomesticStockRepository = DomesticStockRepository(domesticStockService)

    @Provides
    @Singleton
    fun providesStockIconRepository(stockIconService: StockIconService): StockIconRepository = StockIconRepository(stockIconService)
}