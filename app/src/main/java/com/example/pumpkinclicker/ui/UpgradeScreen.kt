// UpgradeScreen.kt
package com.example.pumpkinclicker.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnrememberedMutableState")
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
            onClick = { gameViewModel.buyClickUpgrade(cost = 10 * gameViewModel.clickcant.value) },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Cuchillo: +1 Caramelo/s por click (Coste: ${gameViewModel.clickcant.value * 10} Caramelos) ${gameViewModel.clickcant.value}", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 50 * gameViewModel.cantM1.value, additionalPassivePoints = 1, upgradeType = "M1") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +1 Caramelo/s (Coste: ${gameViewModel.cantM1.value * 50} Caramelos) ${gameViewModel.cantM1.value}", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 100 * gameViewModel.cantM2.value, additionalPassivePoints = 2, upgradeType = "M2") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +2 Caramelos/s (Coste: ${gameViewModel.cantM2.value * 100} Caramelos) ${gameViewModel.cantM2.value}", fontSize = 16.sp)
        }
    }
}

