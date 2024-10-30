package com.example.pumpkinclicker.ui


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_upgrade.*

class UpgradeActivity : AppCompatActivity() {
    private val upgradeCost = 10 // Costo de la mejora

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade)

        buyUpgradeButton.setOnClickListener {
            val mainActivity = ClickActivity()
            if (mainActivity.getClickCount() >= upgradeCost) {
                mainActivity.setClickCount(mainActivity.getClickCount() - upgradeCost)
                Toast.makeText(this, "¡Mejora comprada!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No tienes suficientes puntos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}