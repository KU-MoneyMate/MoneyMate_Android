package com.moneymate.moneymate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.moneymate.moneymate.data.dto.account.response.AccountInfo
import com.moneymate.moneymate.ui.asset.screen.HomeScreen
import com.moneymate.moneymate.ui.finance.screen.FinanceScreen
import com.moneymate.moneymate.ui.manage.screen.ManageScreen
import com.moneymate.moneymate.ui.mypage.screen.MyPageScreen
import com.moneymate.moneymate.ui.asset.screen.AddAccountScreen
import com.moneymate.moneymate.ui.asset.screen.AddAssetScreen
import com.moneymate.moneymate.ui.asset.screen.TransactionHistoryScreen
import com.moneymate.moneymate.ui.auth.screen.LoginScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpIDScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpNameScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpPWScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpPhoneScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpVerificationScreen
import kotlinx.serialization.json.Json
import com.moneymate.moneymate.ui.manage.screen.RetireInputScreen
import com.moneymate.moneymate.ui.manage.screen.RetireResultScreen

@Composable
fun MoneyMateNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Login.route
    ){
        /* 인증/인가 */
        composable(route = Route.Login.route) {
            LoginScreen(
                modifier = modifier,
                onLoginClick = {
                    // TODO: 로그인 로직 구현
                    navController.navigate(Route.Home.route)
                },
                onRegisterClick = {
                    navController.navigate(Route.SignUpID.route)
                }
            )
        }
        composable(route = Route.SignUpID.route) {
            SignUpIDScreen(
                modifier = modifier,
                onNext = {
                    navController.navigate(Route.SignUpPW.route)
                }
            )
        }
        composable(route = Route.SignUpPW.route) {
            SignUpPWScreen(
                modifier = modifier,
                onNext = {
                    navController.navigate(Route.SignUpName.route)
                }
            )
        }
        composable(route = Route.SignUpName.route) {
            SignUpNameScreen(
                modifier = modifier,
                onNext = {
                    navController.navigate(Route.SignUpPhone.route)
                }
            )
        }
        composable(route = Route.SignUpPhone.route) {
            SignUpPhoneScreen(
                modifier = modifier,
                onNext = {
                    navController.navigate(Route.SignUpVerification.route)
                }
            )
        }
        composable(route = Route.SignUpVerification.route) {
            SignUpVerificationScreen(
                modifier = modifier,
                onComplete = {
                    navController.navigate(Route.Login.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                    }
                }
            )
        }

        /*자산 조회(홈)*/
        composable(route = Route.Home.route) {
            HomeScreen(
                modifier = modifier,
                onAddAccountClick = { accountType ->
                    navController.navigate("${Route.AddAccount.route}/$accountType")
                },
                onAddAssetClick = { assetType ->
                    navController.navigate("${Route.AddAsset.route}/$assetType")
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
        composable(
            route = "${Route.AddAsset.route}/{assetType}",
        ) { backStackEntry ->
            val assetType = backStackEntry.arguments?.getString("assetType") ?: ""
            AddAssetScreen(
                modifier = modifier,
                assetType = assetType,
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

        /* 금융 정보 */
        composable(route = Route.Finance.route) {
            FinanceScreen(
                modifier = modifier
            )
        }

        /* 자산 관리 */
        composable(route = Route.Manage.route) {
            ManageScreen(
                modifier = modifier,
                onRetireClick = {
                    navController.navigate(Route.RetireGraph.route)
                }
            )
        }
        retireNavGraph(navController,modifier)
//        //은퇴시뮬레이션
//        composable(route = Route.RetireInput.route) {
//            RetireInputScreen(
//                modifier = modifier,
//                onNavigateBack = { navController.navigateUp() },
//                onNavigateToRetireResult = {
//                    navController.navigate(Route.RetireResult.route)
//                }
//            )
//        }
//        //은퇴시뮬레이션 결과
//        composable(route = Route.RetireResult.route) {
//            RetireResultScreen(
//                modifier = modifier,
//                onNavigateBack = { navController.navigateUp() }
//            )
//        }

        /* 마이페이지 */
        composable(route = Route.MyPage.route) {
            MyPageScreen(
                modifier = modifier
            )
        }
    }
}