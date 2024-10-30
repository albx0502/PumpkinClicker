package com.example.pumpkinclicker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_trivia.*
import kotlin.random.Random

class TriviaActivity : AppCompatActivity() {
    private val bonusPoints = 5 // Puntos de bonificación

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trivia)

        loadTriviaQuestion()

        answerButton1.setOnClickListener { checkAnswer(answerButton1.text.toString()) }
        answerButton2.setOnClickListener { checkAnswer(answerButton2.text.toString()) }
        answerButton3.setOnClickListener { checkAnswer(answerButton3.text.toString()) }
    }

    private fun loadTriviaQuestion() {
        val questions = listOf("¿Cuál es el origen de Halloween?", "¿Qué fruta es típica de Halloween?", "¿Qué se dice que viene a la Tierra en Halloween?")
        val answers = listOf(
            listOf("Samhain", "Navidad", "Pascua"),
            listOf("Calabaza", "Manzana", "Sandía"),
            listOf("Brujas", "Elfos", "Conejos")
        )
        val correctAnswerIndex = Random.nextInt(questions.size)

        questionTextView.text = questions[correctAnswerIndex]
        answerButton1.text = answers[correctAnswerIndex][0]
        answerButton2.text = answers[correctAnswerIndex][1]
        answerButton3.text = answers[correctAnswerIndex][2]
    }

    private fun checkAnswer(answer: String) {
        if (answer == "Samhain" || answer == "Calabaza" || answer == "Brujas") {
            val mainActivity = ClickActivity()
            mainActivity.setClickCount(mainActivity.getClickCount() + bonusPoints)
            Toast.makeText(this, "¡Respuesta correcta! Ganaste puntos bonus", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show()
        }
    }
}