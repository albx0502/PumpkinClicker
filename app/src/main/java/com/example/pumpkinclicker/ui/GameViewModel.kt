package com.example.pumpkinclicker.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.util.Log

class GameViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    var points = mutableStateOf(0)
        private set
    var pointsPerClick = 1
    var passivePoints = 0
    private var passiveIncomeJob: Job? = null

    private var isPointsLoaded = false // Indica si los puntos se han cargado
    private var isUpgradesLoaded = false // Indica si las mejoras se han cargado

    init {
        val playerId = "playerId"
        loadPoints(playerId) { checkDataLoaded() }
        loadUpgrade(playerId) { checkDataLoaded() }
    }

    // Verifica si ambos datos están cargados
    private fun checkDataLoaded() {
        if (isPointsLoaded && isUpgradesLoaded) {
            startPassiveIncome()
        }
    }

    // Función para agregar puntos al hacer clic
    fun addPoints() {
        points.value += pointsPerClick
        savePoints("playerId")
    }

    // Función para comprar una mejora de clic
    fun buyClickUpgrade(cost: Int) {
        if (points.value >= cost) {
            points.value -= cost
            pointsPerClick++
            saveUpgrade("playerId")
        }
    }

    // Función para comprar una mejora de ingresos pasivos
    fun buyUpgrade(cost: Int, additionalPassivePoints: Int) {
        if (points.value >= cost) {
            points.value -= cost
            passivePoints += additionalPassivePoints
            saveUpgrade("playerId")
        }
    }

    // Guardar puntos en Firebase
    private fun savePoints(playerId: String) {
        val scoreData = hashMapOf("points" to points.value)
        db.collection("scores").document(playerId)
            .set(scoreData)
            .addOnSuccessListener { Log.d("Firebase", "Puntos guardados exitosamente") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al guardar puntos", e) }
    }

    // Cargar puntos desde Firebase
    fun loadPoints(playerId: String, onComplete: () -> Unit) {
        db.collection("scores").document(playerId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.contains("points")) {
                    points.value = document.getLong("points")?.toInt() ?: 0
                }
                isPointsLoaded = true
                onComplete()
            }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al cargar puntos", e) }
    }

    // Guardar mejoras en Firebase
    private fun saveUpgrade(playerId: String) {
        val upgradeData = hashMapOf("pointsPerClick" to pointsPerClick, "passivePoints" to passivePoints)
        db.collection("upgrades").document(playerId)
            .set(upgradeData)
            .addOnSuccessListener { Log.d("Firebase", "Mejoras guardadas exitosamente") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al guardar mejoras", e) }
    }

    // Cargar mejoras desde Firebase
    fun loadUpgrade(playerId: String, onComplete: () -> Unit) {
        db.collection("upgrades").document(playerId).get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    pointsPerClick = document.getLong("pointsPerClick")?.toInt() ?: 1
                    passivePoints = document.getLong("passivePoints")?.toInt() ?: 0
                }
                isUpgradesLoaded = true
                onComplete()
            }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al cargar mejoras", e) }
    }

    // Iniciar ingreso pasivo
    private fun startPassiveIncome() {
        passiveIncomeJob?.cancel()
        passiveIncomeJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                points.value += passivePoints
                savePoints("playerId")
            }
        }
    }
}
