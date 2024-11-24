package com.example.countriesapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.countriesapi.R
import com.example.countriesapi.ui.viewmodels.CountryViewModel


@Composable
fun CountryTriviaScreen(navController: NavController, viewModel: CountryViewModel = viewModel()) {
    // Obtenemos el estado actual del juego desde el ViewModel
    val triviaState by viewModel.triviaUiState.collectAsState()

    // Verificamos si el juego ha terminado
    if (triviaState.gameFinished) {
        // Navegamos a SaveScoreScreen pasando el puntaje final
        LaunchedEffect(Unit) {
            navController.navigate("save_score/${triviaState.currentScore}")
        }
    } else {
        // Si el juego no ha terminado, mostramos la pregunta actual
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Mostrar la bandera del país
            AsyncImage(
                model = triviaState.currentImage, // Asegúrate de que esta propiedad contiene la URL de la bandera
                contentDescription = stringResource(id = R.string.txt_banderade)+" ${triviaState.countryName}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), // Ajusta el tamaño de la bandera según sea necesario
                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Mostrar el puntaje actual
            Text(stringResource(id = R.string.txt_puntaje)+"${triviaState.currentScore}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Mostrar las respuestas correctas e incorrectas
            Text(
                stringResource(id = R.string.txt_correctas)+" ${triviaState.correctAnswers}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Text(
                stringResource(id = R.string.txt_incorrectas)+" ${triviaState.incorrectAnswers}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Mostrar la pregunta
            Text(stringResource(id = R.string.txt_pregunta)+" ${triviaState.countryName}?", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(20.dp))

            // Mostrar las opciones de respuesta
            triviaState.options.forEach { option ->
                TriviaOptionButton(
                    option = option,
                    isCorrect = if (triviaState.selectedAnswer == option) triviaState.isAnswerCorrect else null,
                    onClick = { viewModel.checkAnswer(option) }
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }

}

@Composable
fun TriviaOptionButton(option: String, isCorrect: Boolean?, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = when (isCorrect) {
                true -> Color.Green
                false -> Color.Red
                else -> Color.LightGray
            }
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(option, fontSize = 16.sp, color = Color.Black)
    }
}

@Preview(showBackground = true)
@Composable
fun CountryTriviaScreenPreview() {
    val navController = rememberNavController() // Creamos un NavController simulado
    CountryTriviaScreen(navController = navController) // Pasamos el NavController simulado
}