package com.moneymate.moneymate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moneymate.moneymate.ui.manage.screen.RetireInputScreen
import com.moneymate.moneymate.ui.manage.screen.RetireResultScreen

fun NavGraphBuilder.retireNavGraph(
    navController: NavHostController,
    modifier: androidx.compose.ui.Modifier
) {
    navigation(
        startDestination = Route.RetireInput.route,
        route = Route.RetireGraph.route // 👉 이 route를 통해 getBackStackEntry("retire_graph") 가능
    ) {
        composable(route = Route.RetireInput.route) {
            RetireInputScreen(
                modifier = modifier,
                navController = navController, // 반드시 전달
                onNavigateBack = { navController.navigateUp() },
                onNavigateToRetireResult = {
                    navController.navigate(Route.RetireResult.route)
                }
            )
        }

        composable(route = Route.RetireResult.route) {
            RetireResultScreen(
                modifier = modifier,
                navController = navController, // 반드시 전달
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}