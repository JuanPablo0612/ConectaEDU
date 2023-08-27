package com.conectaedu.android.ui.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.conectaedu.android.ui.login.LoginScreen

@Composable
fun Navigation(viewModel: NavigationViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState
    val navController = rememberNavController()

    val startDestination =
        if (uiState.isLoggedIn) Screen.Login.route else Screen.Welcome.route

    if (!uiState.isLoading) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(
                route = Screen.Login.route,
                enterTransition = {
                    slideInHorizontally { height -> height }
                },
                exitTransition = {
                    slideOutHorizontally { height -> -height }
                },
                popEnterTransition = {
                    slideInHorizontally { height -> -height }
                },
                popExitTransition = {
                    slideOutHorizontally { height -> height }
                }
            ) {
                LoginScreen(navController = navController)
            }
        }
    }
}