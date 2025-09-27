package com.moneymate.moneymate.ui.navigation

import SpendingStatisticsScreen
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.data.dto.finance.response.CreditLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.DepositProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.MortgageLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.RentHouseLoanProductItemDto
import com.moneymate.moneymate.data.dto.finance.response.SavingProductItemDto
import com.moneymate.moneymate.ui.asset.screen.AddAccountScreen
import com.moneymate.moneymate.ui.asset.screen.AddAssetScreen
import com.moneymate.moneymate.ui.asset.screen.HomeScreen
import com.moneymate.moneymate.ui.asset.screen.StockHoldingScreen
import com.moneymate.moneymate.ui.asset.screen.TransactionHistoryScreen
import com.moneymate.moneymate.ui.finance.screen.FinanceScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.CreditLoanResultScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.DepositResultScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.MortgageLoanResultScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.RentHouseLoanResultScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProduct.SavingResultScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProductListScreen
import com.moneymate.moneymate.ui.finance.screen.FinancialProductScreen
import com.moneymate.moneymate.ui.finance.screen.NewsArticleScreen
import com.moneymate.moneymate.ui.finance.screen.NewsPublisherHomeScreen
import com.moneymate.moneymate.ui.finance.screen.NewsScreen
import com.moneymate.moneymate.ui.manage.screen.AssetStatisticsScreen
import com.moneymate.moneymate.ui.manage.screen.ManageScreen
import com.moneymate.moneymate.ui.mypage.screen.MyPageScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun MoneyMateNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "authGraph"
    ) {
        /* 인증/인가 */
        authNavGraph(navController, modifier)

        /*자산 조회(홈)*/
        composable(route = Route.Home.route) {
            HomeScreen(
                modifier = modifier,
                onAddAccountClick = { accountType ->
                    navController.navigate("${Route.AddAccount.route}/$accountType")
                },
                onAddAssetClick = { assetType ->
                    navController.navigate(Route.AddAsset.route)
                },
                onStockClick = {
                    navController.navigate(Route.StockHolding.route)
                },
                onAccountItemClick = { accountInfo ->
                    val accountInfoJson = Json.encodeToString(accountInfo)
                    navController.navigate("${Route.TransactionHistory.route}/$accountInfoJson")
                }
            )
        }
        // 계좌 추가 화면
        composable(
            route = "${Route.AddAccount.route}/{accountType}",
        ) { backStackEntry ->
            val accountType = backStackEntry.arguments?.getString("accountType") ?: ""
            AddAccountScreen(
                modifier = modifier,
                accountType = accountType,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        // 자산 추가 화면
        composable(route = Route.AddAsset.route,) {
            AddAssetScreen(
                modifier = modifier,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        // 거래내역 조회 화면
        composable(
            route = "${Route.TransactionHistory.route}/{accountInfo}",
        ) { backStackEntry ->
            val accountInfoJson = backStackEntry.arguments?.getString("accountInfo") ?: ""
            val accountInfo = Json.decodeFromString<AccountInfo>(accountInfoJson)
            TransactionHistoryScreen(
                modifier = modifier,
                accountInfo = accountInfo,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(route = Route.StockHolding.route) {
            StockHoldingScreen(
                modifier = modifier,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        /* 금융 정보 */
        composable(route = Route.Finance.route) {
            FinanceScreen(
                modifier = modifier,
                onNewsClick = { navController.navigate(Route.News.route) },
                onProductClick = { navController.navigate(Route.ProductGraph.route) }
            )
        }
        //경제뉴스 조회 화면
        composable(
            route = Route.News.route,
        ) {
            NewsScreen(
                modifier = modifier,
                onAddClick = { enum ->
                    navController.navigate("${Route.NewsPublisherHome.route}/$enum")
                },
                onArticleClick = { url ->
                    val encodedUrl = URLEncoder.encode(url, "UTF-8")
                    navController.navigate("${Route.NewsArticle.route}/$encodedUrl")
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        //언론사별 홈 화면
        composable(
            route = "${Route.NewsPublisherHome.route}/{enum}",
        ) { backStackEntry ->
            val publisher = backStackEntry.arguments?.getString("enum") ?: ""
            NewsPublisherHomeScreen(
                modifier = modifier,
                publisher = publisher,
                onArticleClick = { url ->
                    val encodedUrl = URLEncoder.encode(url, "UTF-8")
                    navController.navigate("${Route.NewsArticle.route}/$encodedUrl")
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        //기사 화면
        composable(
            route = "${Route.NewsArticle.route}/{url}",
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val url = URLDecoder.decode(encodedUrl, "UTF-8")
            NewsArticleScreen(
                modifier = modifier,
                url = url,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        //은행 상품 정보
        navigation(
            startDestination = Route.Product.route,
            route = Route.ProductGraph.route
        ) {
            composable(route = Route.Product.route) {
                FinancialProductScreen(
                    modifier = Modifier,
                    navController = navController,
                    onNavigateBack = { navController.navigateUp() },
                    onNavigateToDepositList = { navController.navigate(Route.ProductList.route) },
                    onNavigateToSavingList = { navController.navigate(Route.ProductList.route) },
                    onNavigateToMortgageLoanList = { navController.navigate(Route.ProductList.route) },
                    onNavigateToRentHouseLoanList = { navController.navigate(Route.ProductList.route) },
                    onNavigateToCreditLoanList = { navController.navigate(Route.ProductList.route) }
                )
            }
            composable(route = Route.ProductList.route) {
                FinancialProductListScreen(
                    modifier = modifier,
                    navController = navController,
                    onNavigateBack = { navController.navigateUp() },
                    onDepositClick = { item ->
                        val json = Json.encodeToString(DepositProductItemDto.serializer(), item)
                            .replace("%", "_PERCENT_").replace("\n", "_NEWLINE_")
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate("${Route.ProductDeposit.route}/$encoded")
                    },
                    onSavingClick = { item ->
                        val json = Json.encodeToString(SavingProductItemDto.serializer(), item)
                            .replace("%", "_PERCENT_").replace("\n", "_NEWLINE_")
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate("${Route.ProductSaving.route}/$encoded")
                    },
                    onMortgageLoanClick = { item ->
                        val json = Json.encodeToString(MortgageLoanProductItemDto.serializer(), item)
                            .replace("%", "_PERCENT_").replace("\n", "_NEWLINE_")
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate("${Route.ProductMortgageLoan.route}/$encoded")
                    },
                    onRentHouseLoanClick = { item ->
                        val json =
                            Json.encodeToString(RentHouseLoanProductItemDto.serializer(), item)
                                .replace("%", "_PERCENT_").replace("\n", "_NEWLINE_")
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate("${Route.ProductRentHouseLoan.route}/$encoded")
                    },
                    onCreditLoanClick = { item ->
                        val json =
                            Json.encodeToString(CreditLoanProductItemDto.serializer(), item)
                                .replace("%", "_PERCENT_").replace("\n", "_NEWLINE_")
                        val encoded = URLEncoder.encode(json, "UTF-8")
                        navController.navigate("${Route.ProductCreditLoan.route}/$encoded")
                    }
                )
            }
            composable(
                route = "${Route.ProductDeposit.route}/{item}",
                arguments = listOf(navArgument("item") { type = NavType.StringType })
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString("item").orEmpty()
                val json = URLDecoder.decode(encoded, "UTF-8")
                    .replace("_PERCENT_", "%").replace("_NEWLINE_", "\n")
                val item = Json.decodeFromString(DepositProductItemDto.serializer(), json)
                DepositResultScreen(
                    modifier = modifier,
                    onNavigateBack = { navController.navigateUp() },
                    item = item
                )
            }

            composable(
                route = "${Route.ProductSaving.route}/{item}",
                arguments = listOf(navArgument("item") { type = NavType.StringType })
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString("item").orEmpty()
                val json = URLDecoder.decode(encoded, "UTF-8")
                    .replace("_PERCENT_", "%").replace("_NEWLINE_", "\n")
                val item = Json.decodeFromString(SavingProductItemDto.serializer(), json)
                SavingResultScreen(
                    modifier = modifier,
                    onNavigateBack = { navController.navigateUp() },
                    item = item
                )
            }

            composable(
                route = "${Route.ProductMortgageLoan.route}/{item}",
                arguments = listOf(navArgument("item") { type = NavType.StringType })
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString("item").orEmpty()
                val json = URLDecoder.decode(encoded, "UTF-8")
                    .replace("_PERCENT_", "%").replace("_NEWLINE_", "\n")
                val item = Json.decodeFromString(MortgageLoanProductItemDto.serializer(), json)
                MortgageLoanResultScreen(
                    modifier = modifier,
                    onNavigateBack = { navController.navigateUp() },
                    item = item
                )
            }

            composable(
                route = "${Route.ProductRentHouseLoan.route}/{item}",
                arguments = listOf(navArgument("item") { type = NavType.StringType })
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString("item").orEmpty()
                val json = URLDecoder.decode(encoded, "UTF-8")
                    .replace("_PERCENT_", "%").replace("_NEWLINE_", "\n")
                val item = Json.decodeFromString(RentHouseLoanProductItemDto.serializer(), json)
                RentHouseLoanResultScreen(
                    modifier = modifier,
                    onNavigateBack = { navController.navigateUp() },
                    item = item
                )
            }
            composable(
                route = "${Route.ProductCreditLoan.route}/{item}",
                arguments = listOf(navArgument("item") { type = NavType.StringType })
            ) { backStackEntry ->
                val encoded = backStackEntry.arguments?.getString("item").orEmpty()
                val json = URLDecoder.decode(encoded, "UTF-8")
                    .replace("_PERCENT_", "%").replace("_NEWLINE_", "\n")
                val item = Json.decodeFromString(CreditLoanProductItemDto.serializer(), json)
                CreditLoanResultScreen(
                    modifier = modifier,
                    onNavigateBack = { navController.navigateUp() },
                    item = item
                )
            }
        }
        // 정기예금 상세
        composable(
            route = "${Route.ProductDeposit.route}/{item}",
            arguments = listOf(
                navArgument("item") { type = NavType.StringType; nullable = false }
            )
        ) { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("item").orEmpty()
            val json = URLDecoder.decode(encoded, "UTF-8")
            val item = Json.decodeFromString(DepositProductItemDto.serializer(), json)

            DepositResultScreen(
                modifier = modifier,
                onNavigateBack = { navController.navigateUp() },
                item = item
            )
        }

            /* 자산 관리 */
            composable(route = Route.Manage.route) {
                ManageScreen(
                    modifier = modifier,
                    onRetireClick = {
                        navController.navigate(Route.RetireGraph.route)
                    },
                    onAssetStatisticsClick = {
                        navController.navigate(Route.AssetStatistics.route)
                    },
                    onSpendingStatisticsClick = {
                        navController.navigate(Route.SpendingStatistics.route)
                    }
                )
            }
            retireNavGraph(navController, modifier)
            //소비통계 조회 화면
            composable(
                route = Route.SpendingStatistics.route
            ) {
                SpendingStatisticsScreen(
                    modifier = modifier,
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )

            }
            // 자산 변동 통계 조회 화면
            composable(route = Route.AssetStatistics.route) {
                AssetStatisticsScreen(
                    modifier = modifier,
                    onNavigateBack = {
                        navController.navigateUp()
                    }
                )
            }

            /* 마이페이지 */
            composable(route = Route.MyPage.route) {
                MyPageScreen(
                    modifier = modifier
                )
            }
        }
    }