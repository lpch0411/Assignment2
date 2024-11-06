package com.cst3115.enterprise.assignment2

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cst3115.enterprise.assignment2.ui.theme.SettingsScreen
import com.cst3115.enterprise.assignment2.ui.theme.WeatherViewModel

@Composable
fun WeatherAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            val viewModel: WeatherViewModel = viewModel()
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}