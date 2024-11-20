package com.example.pumpkinclicker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar Firebase en la aplicación
        FirebaseApp.initializeApp(this)

        setContent {
            NavigationWrapper() // Llamada directa a la función de navegación
        }
    }
}
