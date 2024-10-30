// GameViewModel.kt
package com.example.pumpkinclicker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    var points = mutableStateOf(0)
        private set
    var pointsPerClick = 1
    var passivePoints = 0
    private var passiveIncomeJob: Job? = null

    init {
        startPassiveIncome()
    }

    fun addPoints() {
        points.value += pointsPerClick
    }

    fun buyUpgrade(cost: Int, additionalPassivePoints: Int) {
        if (points.value >= cost) {
            points.value -= cost
            passivePoints += additionalPassivePoints
        }
    }

    private fun startPassiveIncome() {
        passiveIncomeJob?.cancel() // Cancela el ingreso pasivo anterior si ya existía
        passiveIncomeJob = viewModelScope.launch {
            while (true) {
                delay(1000) // Incrementa puntos cada segundo
                points.value += passivePoints
            }
        }
    }
}
