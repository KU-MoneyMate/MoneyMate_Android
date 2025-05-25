package com.kuit.moneymate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kuit.moneymate.ui.asset.HomeScreen
import com.kuit.moneymate.ui.finance.FinanceScreen
import com.kuit.moneymate.ui.manage.ManageScreen
import com.kuit.moneymate.ui.mypage.MyPageScreen

@Composable
fun MoneyMateNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ){
        // 인증/인가


        // 자산 조회(홈)
        composable(route = Route.Home.route) {
            HomeScreen(
                modifier = modifier
            )
        }

        // 금융 정보
        composable(route = Route.Finance.route) {
            FinanceScreen(
                modifier = modifier
            )
        }

        // 자산 관리
        composable(route = Route.Manage.route) {
            ManageScreen(
                modifier = modifier
            )
        }

        // 마이페이지
        composable(route = Route.MyPage.route) {
            MyPageScreen(
                modifier = modifier
            )
        }
    }
}