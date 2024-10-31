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
    val clickcant = remember { mutableStateOf(1) }
    val cantM1 = remember { mutableStateOf(1) }
    val cantM2 = remember { mutableStateOf(1) }
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
        Button(onClick = {gameViewModel.buyClickUpgrade(cost = 10 * clickcant.value)
            clickcant.value++},

                modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)

        ){
            Text(text = "Cuchillo: +1 Caramelo/s por click(Coste: ${clickcant.value * 10} Caramelos) ${clickcant.value}", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 50 * cantM1.value, additionalPassivePoints = 1)
                      cantM1.value++},
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +1 Caramelo/s (Coste: ${cantM1.value*50} Caramelos)${cantM1.value}", fontSize = 16.sp)
        }

        Button(
            onClick = { gameViewModel.buyUpgrade(cost = 100 * cantM2.value, additionalPassivePoints = 2)
                      cantM2.value++},
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +2 Caramelos/s (Coste: ${cantM2.value *100} Caramelos)${cantM2.value}", fontSize = 16.sp)
        }
    }
}
