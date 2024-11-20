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
    private var playerId: String? = null // UID del usuario autenticado

    var points = mutableStateOf(0)
        private set
    var pointsPerClick = 1
    var passivePoints = 0
    var cuchillos = mutableStateOf(1)
    var tumbas = mutableStateOf(1)
    var zombies = mutableStateOf(1)
    var vampiros = mutableStateOf(1)
    var hombreslobo = mutableStateOf(1)

    private var passiveIncomeJob: Job? = null

    // Inicializar el ViewModel con el playerId
    fun initialize(playerId: String) {
        stopPassiveIncome() // Detener ingresos pasivos previos
        resetGameData() // Reiniciar datos
        this.playerId = playerId
        loadUserData() // Cargar datos del usuario desde Firebase
    }

    // Cargar datos del usuario desde Firebase
    private fun loadUserData() {
        val userId = playerId ?: return
        db.collection("users").document(userId).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    points.value = document.getLong("points")?.toInt() ?: 0
                    pointsPerClick = document.getLong("pointsPerClick")?.toInt() ?: 1
                    passivePoints = document.getLong("passivePoints")?.toInt() ?: 0
                    cuchillos.value = document.getLong("cuchillos")?.toInt() ?: 1
                    tumbas.value = document.getLong("tumbas")?.toInt() ?: 1
                    zombies.value = document.getLong("zombies")?.toInt() ?: 1
                    vampiros.value = document.getLong("vampiros")?.toInt() ?: 1
                    hombreslobo.value = document.getLong("hombreslobo")?.toInt() ?: 1
                    startPassiveIncome() // Comenzar ingresos pasivos
                } else {
                    saveUserData() // Si el documento no existe, crea uno nuevo
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al cargar datos del usuario", e)
            }
    }

    // Guardar datos del usuario en Firebase
    private fun saveUserData() {
        val userId = playerId ?: return
        val userData = hashMapOf(
            "points" to points.value,
            "pointsPerClick" to pointsPerClick,
            "passivePoints" to passivePoints,
            "cuchillos" to cuchillos.value,
            "tumbas" to tumbas.value,
            "zombies" to zombies.value,
            "vampiros" to vampiros.value,
            "hombreslobo" to hombreslobo.value
        )
        db.collection("users").document(userId).set(userData)
            .addOnSuccessListener { Log.d("Firebase", "Datos guardados exitosamente") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al guardar datos", e) }
    }

    // Guardar puntos
    private fun savePoints() {
        val userId = playerId ?: return
        db.collection("users").document(userId)
            .update("points", points.value)
            .addOnSuccessListener { Log.d("Firebase", "Puntos guardados exitosamente") }
            .addOnFailureListener { e -> Log.e("Firebase", "Error al guardar puntos", e) }
    }

    // Agregar puntos
    fun addPoints() {
        points.value += pointsPerClick
        savePoints()
    }

    // Comprar mejoras
    fun buyClickUpgrade(cost: Int) {
        if (points.value >= cost) {
            points.value -= cost
            pointsPerClick++
            cuchillos.value++
            saveUserData()
        }
    }

    fun buyUpgrade(cost: Int, additionalPassivePoints: Int, upgradeType: String? = null) {
        if (points.value >= cost) {
            points.value -= cost
            passivePoints += additionalPassivePoints
            upgradeType?.let {
                when (it) {
                    "Tumbas" -> tumbas.value++
                    "Zombies" -> zombies.value++
                    "Vampiros" -> vampiros.value++
                    "Hombres Lobo" -> hombreslobo.value++
                    else -> Log.w("GameViewModel", "Tipo de mejora desconocido: $it")
                }
            }
            saveUserData()
        }
    }

    // Iniciar ingresos pasivos
    private fun startPassiveIncome() {
        passiveIncomeJob?.cancel()
        passiveIncomeJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                points.value += passivePoints
                savePoints()
            }
        }
    }

    // Detener ingresos pasivos
    fun stopPassiveIncome() {
        passiveIncomeJob?.cancel()
        passiveIncomeJob = null
    }

    // Reiniciar datos del juego
    fun resetGameData() {
        stopPassiveIncome()
        points.value = 0
        pointsPerClick = 1
        passivePoints = 0
        cuchillos.value = 1
        tumbas.value = 1
        zombies.value = 1
        vampiros.value = 1
        hombreslobo.value = 1
    }





    fun payForTrivia(cost: Int): Boolean {
        return if (points.value >= cost) {
            points.value -= cost
            true
        } else {
            false
        }
    }

    fun rewardTrivia(success: Boolean, bonus: Int) {
        if (success) {
            points.value += bonus
        }
    }
}
