// UpgradeScreen.kt
package com.example.pumpkinclicker.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 50.dp)
    ){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = "Caramelos: ${gameViewModel.points.value}",
            fontSize = 28.sp,
            color = Color(0xFFFFA500),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Text(
            text = "(${gameViewModel.passivePoints} Caramelos/s)",
            fontSize = 18.sp,
            color = Color(0xFFFFA500),
            modifier = Modifier.padding(bottom = 24.dp)
        )
        Button(
            onClick = { gameViewModel.buyClickUpgrade(cost = 10 * (gameViewModel.cuchillos.value*10)) },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Cuchillo: +1 Caramelo/s por click (Coste: ${(gameViewModel.cuchillos.value*10) * 10} Caramelos)  ${gameViewModel.cuchillos.value -1} Cuchillos", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 50 * gameViewModel.tumbas.value, additionalPassivePoints = 1, upgradeType = "Tumbas") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Tumbas: +1 Caramelo/s (Coste: ${gameViewModel.tumbas.value * 50} Caramelos) ${gameViewModel.tumbas.value -1} Tumbas", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 100 * gameViewModel.zombies.value, additionalPassivePoints = 10, upgradeType = "Zombies") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Zombies: +10 Caramelos/s (Coste: ${gameViewModel.zombies.value * 100} Caramelos) ${gameViewModel.zombies.value-1} Zombies", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 1000 * gameViewModel.vampiros.value, additionalPassivePoints = 30, upgradeType = "Vampiros") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Vampiros: +30 Caramelos/s (Coste: ${gameViewModel.vampiros.value * 1000} Caramelos) ${gameViewModel.vampiros.value-1} Vampiros ", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 10000 * gameViewModel.hombreslobo.value, additionalPassivePoints = 30, upgradeType = "Hombres Lobo") },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Hombres Lobo: +110 Caramelos/s (Coste: ${gameViewModel.hombreslobo.value * 1000} Caramelos) ${gameViewModel.hombreslobo.value-1} Hombres Lobo ", fontSize = 16.sp)
        }
    }
        }
}

