// ClickScreen.kt
package com.example.pumpkinclicker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pumpkinclicker.R

@Composable
fun ClickScreen(gameViewModel: GameViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // Título del contador de puntos
        Text(
            text = "Caramelos: ${gameViewModel.points.value}",
            fontSize = 28.sp,
            color = Color(0xFFFFA500),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Imagen de la calabaza como botón de clic
        Image(
            painter = painterResource(id = R.drawable.calabaza),
            contentDescription = "Botón de calabaza",
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
                .clickable { gameViewModel.addPoints() }
        )
    }
}
