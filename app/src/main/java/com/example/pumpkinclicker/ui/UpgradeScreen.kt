// UpgradeScreen.kt
package com.example.pumpkinclicker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun UpgradeScreen(gameViewModel: GameViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Caramelos: ${gameViewModel.points.value}",
            fontSize = 28.sp,
            color = Color(0xFFFFA500),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 50, additionalPassivePoints = 1) },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +1 Caramelo/s (Coste: 50 Caramelos)", fontSize = 16.sp)
        }

        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 100, additionalPassivePoints = 2) },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +2 Caramelos/s (Coste: 100 Caramelos)", fontSize = 16.sp)
        }
    }
}
