package com.example.pumpkinclicker.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.pumpkinclicker.R

@Composable
fun TriviaScreen(navController: NavHostController, gameViewModel: GameViewModel) {
    val triviaCost = 20
    var hasPaid by remember { mutableStateOf(false) }
    var questionIndex by remember { mutableStateOf(0) }
    val questions = listOf(
        Question("¿Quién es este personaje?", "Michael Myers", listOf("Jason", "GhostFace", "Michael Myers", "Freddy Krueger"), R.drawable.michaelmyers),
        Question("¿En qué país se originó Halloween?", "Irlanda", listOf("Francia", "Estados Unidos", "Escocia", "Irlanda")),
        Question("¿Nombre de la película donde un muñeco cobra vida?", "Chucky", listOf("Chucky", "Anabelle", "Puppet Master", "Gremlins")),
        Question("¿Cuál es el símbolo tradicional de Halloween?", "Calabaza Tallada", listOf("Arbol de navidad","Calabaza Tallada","Huevo decorado","Piñata")),
        Question("¿Que se acostumbra a pedir en halloween?", "Dulces", listOf("Bebidas","Chocolate caliente","Dulces","Dinero")),
        Question("¿De qué país es originaria la tradición del “Trick or Treat” o “Dulce o Truco”??",  "Estados Unidos", listOf("Estados Unidos","Canada","Mexico","Reino unido")),
        Question("¿Qué animal es comúnmente asociado con las brujas en Halloween?", "Gato negro", listOf("Gato negro","Araña","Delfin rosado","Murcielago")),
        Question("¿Cuál de estos disfraces es popular en Halloween?", "Momia", listOf("Hadas","Dinosaurio","Heroe Griego","Momia")),
        Question( "¿Qué famoso monstruo se transforma con la luna llena?", "Hombre lobo", listOf("Vampiro","Hombre lobo","Zombi","Fantasma")),
        Question("¿Qué nombre recibe el acto de crear historias o películas de terror para asustar a otros?", "Horror", listOf("Terrorismo","Espanto","Miedo","Horror")),
    )

    if (!hasPaid) {
        WelcomeScreen(
            onPlay = {
                if (gameViewModel.payForTrivia(triviaCost)) {
                    hasPaid = true
                }
            },
            onCancel = { navController.popBackStack() }
        )
    } else {
        if (questionIndex >= questions.size) {
            Text(
                text = "¡Felicidades! Has completado el trivia.",
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
            return
        }

        val question = questions[questionIndex]
        var selectedAnswer by remember { mutableStateOf<String?>(null) }
        var showResult by remember { mutableStateOf(false) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (question.imageRes != null) {
                Image(
                    painter = painterResource(id = question.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(8.dp)
                )
            }

            Text(text = question.text, fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))

            question.answers.forEach { answer ->
                Button(
                    onClick = {
                        selectedAnswer = answer
                        showResult = true
                        if (answer == question.correctAnswer) {
                            gameViewModel.rewardTrivia(success = true, bonus = triviaCost * 2)
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = answer)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showResult) {
                Text(
                    text = if (selectedAnswer == question.correctAnswer) "¡Correcto!" else "Incorrecto",
                    color = if (selectedAnswer == question.correctAnswer) Color.Green else Color.Red,
                    fontSize = 20.sp
                )
                Button(onClick = {
                    showResult = false
                    questionIndex++
                }) {
                    Text("Siguiente pregunta")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}

@Composable
fun WelcomeScreen(onPlay: () -> Unit, onCancel: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "¿Quieres jugar al trivia?", fontSize = 24.sp, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Cuesta 20 caramelos", fontSize = 18.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onPlay) {
            Text("Jugar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onCancel) {
            Text("Cancelar")
        }
    }
}

data class Question(
    val text: String,
    val correctAnswer: String,
    val answers: List<String>,
    val imageRes: Int? = null
)
