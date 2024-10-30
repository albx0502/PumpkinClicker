// ClickScreen.kt
package com.example.pumpkinclicker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pumpkinclicker.R

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
        // Muestra el contador de puntos
        Text(text = "Puntos: $clickCount", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Imagen de calabaza que actúa como botón
        Image(
            painter = painterResource(id = R.drawable.calabaza),  // Asegúrate de que el nombre coincida con tu archivo
            contentDescription = "Botón de calabaza",
            modifier = Modifier
                .size(150.dp)
                .clickable { clickCount += pointsPerClick }  // Incrementa el contador al hacer clic en la calabaza
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ir a la pantalla de mejoras
        Button(onClick = { navController.navigate("upgrade_screen") }) {
            Text("Ir a Mejoras")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para ir a la pantalla de trivia
        Button(onClick = { navController.navigate("trivia_screen") }) {
            Text("Ir a Trivia")
        }
    }
}
