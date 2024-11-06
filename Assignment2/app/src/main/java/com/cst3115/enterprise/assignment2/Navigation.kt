package com.cst3115.enterprise.assignment2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cst3115.enterprise.assignment2.ui.theme.SettingsScreen

@Composable
fun WeatherAppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}