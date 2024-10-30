// ClickScreen.kt
package com.example.pumpkinclicker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ClickScreen(navController: NavHostController) {
    var clickCount by remember { mutableStateOf(0) }
    val pointsPerClick = 1

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Puntos: $clickCount", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { clickCount += pointsPerClick }) {
            Text("🎃 Click en la Calabaza 🎃")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("upgrade_screen") }) {
            Text("Ir a Mejoras")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("trivia_screen") }) {
            Text("Ir a Trivia")
        }
    }
}
