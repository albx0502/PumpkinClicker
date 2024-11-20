package com.example.pumpkinclicker.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    // Registrar un nuevo usuario
    fun register(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Por favor, rellena todos los campos")
            return
        }

        auth.createUserWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, "Error al registrar: ${task.exception?.localizedMessage}")
                }
            }
    }

    // Iniciar sesión
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Por favor, rellena todos los campos")
            return
        }

        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, "Correo o contraseña incorrectos")
                }
            }
    }

    // Cerrar sesión
    fun logout(onLogoutComplete: () -> Unit) {
        auth.signOut()
        onLogoutComplete()
    }

    // Obtener el UID del jugador actual
    fun getPlayerId(): String? {
        return auth.currentUser?.uid
    }
}
