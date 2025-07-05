package com.moneymate.moneymate.ui.navigation

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.moneymate.moneymate.ui.auth.AuthViewModel
import com.moneymate.moneymate.ui.auth.screen.LoginScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpIDScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpNameScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpPWScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpPhoneScreen
import com.moneymate.moneymate.ui.auth.screen.SignUpVerificationScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    navigation(
        startDestination = Route.Login.route,
        route = "authGraph"
    ) {
        composable(route = Route.Login.route) {
            LoginScreen(
                modifier = modifier,
                onLoginClick = {
                    navController.navigate(Route.Home.route)
                },
                onRegisterClick = {
                    navController.navigate(Route.SignUpID.route)
                }
            )
        }

        composable(route = Route.SignUpID.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("authGraph")
            }
            val authViewModel = hiltViewModel<AuthViewModel>(parentEntry)
            
            SignUpIDScreen(
                modifier = modifier,
                viewModel = authViewModel,
                onNext = {
                    navController.navigate(Route.SignUpPW.route)
                }
            )
        }

        composable(route = Route.SignUpPW.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("authGraph")
            }
            val authViewModel = hiltViewModel<AuthViewModel>(parentEntry)
            
            SignUpPWScreen(
                modifier = modifier,
                viewModel = authViewModel,
                onNext = {
                    navController.navigate(Route.SignUpName.route)
                }
            )
        }

        composable(route = Route.SignUpName.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("authGraph")
            }
            val authViewModel = hiltViewModel<AuthViewModel>(parentEntry)
            
            SignUpNameScreen(
                modifier = modifier,
                viewModel = authViewModel,
                onNext = {
                    navController.navigate(Route.SignUpPhone.route)
                }
            )
        }

        composable(route = Route.SignUpPhone.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("authGraph")
            }
            val authViewModel = hiltViewModel<AuthViewModel>(parentEntry)
            
            SignUpPhoneScreen(
                modifier = modifier,
                viewModel = authViewModel,
                onNext = {
                    navController.navigate(Route.SignUpVerification.route)
                }
            )
        }

        composable(route = Route.SignUpVerification.route) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("authGraph")
            }
            val authViewModel = hiltViewModel<AuthViewModel>(parentEntry)
            
            SignUpVerificationScreen(
                modifier = modifier,
                viewModel = authViewModel,
                onComplete = {
                    navController.navigate(Route.Login.route) {
                        popUpTo(Route.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
} 