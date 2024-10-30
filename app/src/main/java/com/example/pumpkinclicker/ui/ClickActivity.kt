package com.example.pumpkinclicker.ui


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_click.*

class ClickActivity : AppCompatActivity() {
    private var clickCount = 0
    private var pointsPerClick = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_click)

        pumpkinButton.setOnClickListener { clickPumpkin() }

        upgradeButton.setOnClickListener {
            val intent = Intent(this, UpgradeActivity::class.java)
            startActivity(intent)
        }

        triviaButton.setOnClickListener {
            val intent = Intent(this, TriviaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickPumpkin() {
        clickCount += pointsPerClick
        updateClickCountText()
    }

    private fun updateClickCountText() {
        clickCountTextView.text = "Puntos: $clickCount"
    }

    fun getClickCount(): Int = clickCount

    fun setClickCount(newCount: Int) {
        clickCount = newCount
        updateClickCountText()
    }
}