// NavigationWrapper.kt
package com.example.pumpkinclicker.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "click_screen") {
        composable("click_screen") { ClickScreen(navController) }
        composable("upgrade_screen") { UpgradeScreen(navController) }
        composable("trivia_screen") { TriviaScreen(navController) }
    }
}
