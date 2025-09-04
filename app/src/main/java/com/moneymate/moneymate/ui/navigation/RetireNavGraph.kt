package com.moneymate.moneymate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moneymate.moneymate.ui.manage.ManageViewModel
import com.moneymate.moneymate.ui.manage.screen.RetireInputScreen
import com.moneymate.moneymate.ui.manage.screen.RetireResultScreen

fun NavGraphBuilder.retireNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    navigation(
        startDestination = Route.RetireInput.route,
        route = Route.RetireGraph.route
    ) {
        composable(route = Route.RetireInput.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Route.RetireGraph.route)
            }
            val viewModel = hiltViewModel<ManageViewModel>(parentEntry)
            
            RetireInputScreen(
                modifier = modifier,
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() },
                onNavigateToRetireResult = {
                    navController.navigate(Route.RetireResult.route)
                }
            )
        }

        composable(route = Route.RetireResult.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(Route.RetireGraph.route)
            }
            val viewModel = hiltViewModel<ManageViewModel>(parentEntry)
            
            RetireResultScreen(
                modifier = modifier,
                viewModel = viewModel,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}