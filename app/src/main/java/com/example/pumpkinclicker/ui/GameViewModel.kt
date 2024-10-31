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
    var clickcant = mutableStateOf(1)
    var cantM1 = mutableStateOf(1)
    var cantM2 = mutableStateOf(1)
    private var passiveIncomeJob: Job? = null

    init {
        startPassiveIncome()
    }

    fun addPoints() {
        points.value += pointsPerClick
    }

    fun buyClickUpgrade(cost: Int) {
        if (points.value >= cost) {
            points.value -= cost
            pointsPerClick++
            clickcant.value++
        }
    }

    fun buyUpgrade(cost: Int, additionalPassivePoints: Int, upgradeType: String) {
        if (points.value >= cost) {
            points.value -= cost
            passivePoints += additionalPassivePoints
            when (upgradeType) {
                "M1" -> cantM1.value++
                "M2" -> cantM2.value++
            }
        }
    }

    private fun startPassiveIncome() {
        passiveIncomeJob?.cancel()
        passiveIncomeJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                points.value += passivePoints
            }
        }
    }
}

