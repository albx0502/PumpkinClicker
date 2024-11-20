package com.example.pumpkinclicker.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(authViewModel: AuthViewModel, onLoginSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRegistering by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = {
            if (isRegistering) {
                authViewModel.register(email, password) { success, error ->
                    if (success) {
                        onLoginSuccess()
                    } else {
                        errorMessage = error
                    }
                }
            } else {
                authViewModel.login(email, password) { success, error ->
                    if (success) {
                        onLoginSuccess()
                    } else {
                        errorMessage = error
                    }
                }
            }
        }) {
            Text(if (isRegistering) "Registrar" else "Iniciar Sesión")
        }
        TextButton(onClick = { isRegistering = !isRegistering }) {
            Text(if (isRegistering) "¿Ya tienes cuenta? Inicia Sesión" else "¿No tienes cuenta? Regístrate")
        }

        // Mostrar el mensaje de error si existe
        errorMessage?.let {
            Text(text = it, color = Color.Red, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

