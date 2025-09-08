package com.moneymate.moneymate.ui.navigation

import SpendingStatisticsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.ui.asset.screen.AddAccountScreen
import com.moneymate.moneymate.ui.asset.screen.AddAssetScreen
import com.moneymate.moneymate.ui.asset.screen.HomeScreen
import com.moneymate.moneymate.ui.asset.screen.StockHoldingScreen
import com.moneymate.moneymate.ui.asset.screen.TransactionHistoryScreen
import com.moneymate.moneymate.ui.finance.screen.FinanceScreen
import com.moneymate.moneymate.ui.finance.screen.NewsArticleScreen
import com.moneymate.moneymate.ui.finance.screen.NewsPublisherHomeScreen
import com.moneymate.moneymate.ui.finance.screen.NewsScreen
import com.moneymate.moneymate.ui.finance.screen.MarketInfoScreen
import com.moneymate.moneymate.ui.manage.screen.AssetStatisticsScreen
import com.moneymate.moneymate.ui.manage.screen.ManageScreen
import com.moneymate.moneymate.ui.manage.screen.PeerAssetStatisticsScreen
import com.moneymate.moneymate.ui.mypage.screen.MyPageScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun MoneyMateNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.AuthGraph.route
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
        composable(route = Route.StockHolding.route){
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
                onMarketInfoClick = { navController.navigate(Route.MarketInfo.route) }
            )
        }
        //경제뉴스 조회 화면
        composable (
            route = Route.News.route,
        ) { NewsScreen(
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
        composable (
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
        composable (
            route = "${Route.NewsArticle.route}/{url}",
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url") ?: ""
            val url = URLDecoder.decode(encodedUrl, "UTF-8")
            NewsArticleScreen (
                modifier = modifier,
                url = url,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        // 증시 정보 화면
        composable(route = Route.MarketInfo.route){
            MarketInfoScreen(
                modifier = modifier,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        /* 자산 관리 */
        composable(route = Route.Manage.route) {
            ManageScreen(
                modifier = modifier,
                onRetireClick = {
                    navController.navigate(Route.RetireGraph.route)
                },
                onPeerAssetStatisticsClick = {
                    navController.navigate(Route.PeerAssetStatistics.route)
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
        // 소비통계 조회 화면
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
        // 또래 자산 통계 조회 화면
        composable(route = Route.PeerAssetStatistics.route){
            PeerAssetStatisticsScreen(
                modifier = modifier,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
        // 자산 변동 통계 조회 화면
        composable(route = Route.AssetStatistics.route){
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