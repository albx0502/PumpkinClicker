// NavigationWrapper.kt
package com.example.pumpkinclicker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pumpkinclicker.R

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val gameViewModel: GameViewModel = viewModel()  // Estado del juego

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen de fondo
            Image(
                painter = painterResource(id = R.drawable.fondo),  // Asegúrate de que el nombre coincide con el archivo en drawable
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Contenido de navegación en la parte superior del fondo
            NavHost(
                navController = navController,
                startDestination = "click_screen",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("click_screen") { ClickScreen(gameViewModel) }
                composable("upgrade_screen") { UpgradeScreen(gameViewModel) }
                composable("trivia_screen") { TriviaScreen(navController) }
            }
        }
    }
}
