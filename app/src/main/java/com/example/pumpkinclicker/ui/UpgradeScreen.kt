// UpgradeScreen.kt
package com.example.pumpkinclicker.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun UpgradeScreen(gameViewModel: GameViewModel = viewModel()) {
    var cantM1 = 1;
    var cantM2 = 1;
    var clickcant = 1;
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
        Button(onClick = {gameViewModel.buyClickUpgrade(cost = 10 * clickcant)
            clickcant++},

                modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)

        ){
            Text(text = "Cuchillo: +1 Caramelo/s por click(Coste: ${clickcant * 10} Caramelos) $clickcant", fontSize = 16.sp)
        }
        Button(
            onClick = { gameViewModel.buyUpgrade(cost = (50 * cantM1), additionalPassivePoints = 1)
                      cantM1++},
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +1 Caramelo/s (Coste: ${cantM1*50} Caramelos)$cantM1", fontSize = 16.sp)
        }

        Button(
            onClick = { gameViewModel.buyUpgrade(cost = (100 * cantM2), additionalPassivePoints = 2)
                      cantM2++},
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(8.dp)
        ) {
            Text(text = "Mejora: +2 Caramelos/s (Coste: ${cantM2 *100} Caramelos)$cantM2", fontSize = 16.sp)
        }
    }
}
