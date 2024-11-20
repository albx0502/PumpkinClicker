package com.example.pumpkinclicker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        setContent {
            val authViewModel: AuthViewModel = viewModel()
            val gameViewModel: GameViewModel = viewModel()

            if (authViewModel.getPlayerId() == null) {
                LoginScreen(authViewModel) {
                    authViewModel.getPlayerId()?.let { playerId ->
                        gameViewModel.initialize(playerId)
                    }
                    recreate()
                }
            } else {
                authViewModel.getPlayerId()?.let { playerId ->
                    gameViewModel.initialize(playerId)
                }
                NavigationWrapper(
                    onLogout = {
                        gameViewModel.stopPassiveIncome()
                        gameViewModel.resetGameData()
                        authViewModel.logout {
                            recreate()
                        }
                    }
                )
            }
        }
    }
}
