package com.example.countriesapi.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapi.R
import kotlinx.coroutines.delay

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun StartScreen(navController: NavController) {
    // Lista de banderas para el banner
    val flags = listOf(
        R.drawable.argentina, R.drawable.brasil, R.drawable.suiza,
        R.drawable.china, R.drawable.cocos, R.drawable.crica,
        R.drawable.espana, R.drawable.eu, R.drawable.holanda,
        R.drawable.inglaterra, R.drawable.japon, R.drawable.mexico,
        R.drawable.chile
    )

    // Creamos un ScrollState para controlar el desplazamiento
    val scrollState = rememberScrollState()

    // Iniciar el desplazamiento del banner
    LaunchedEffect(true) {
        while (true) {
            delay(50) // Controla la velocidad del desplazamiento (2000ms = 2 segundos)
            if (scrollState.value < scrollState.maxValue) {
                scrollState.scrollTo(scrollState.value + 5) // Desplazar 1 píxel
            } else {
                scrollState.scrollTo(0) // Resetea el desplazamiento al llegar al final
            }
        }
    }

    // Contenido de la pantalla de inicio
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFE8F5E9)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Banner animado con las banderas desplazándose
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(scrollState)
                    .height(150.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    flags.forEach {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = "Flag",
                            modifier = Modifier.size(150.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Título de la app
            Text(
                text = stringResource(id = R.string.titulo_app),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 50.dp)
            )

            // Botón para iniciar el juego
            Button(
                onClick = { navController.navigate("trivia") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_inicia_juego),
                style = TextStyle(fontSize = 20.sp) )
            }

            // Botón para ir a la tabla de puntajes
            Button(
                onClick = { navController.navigate("high_scores") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_puntajes),
                    style = TextStyle(fontSize = 20.sp) )
            }

            Spacer(modifier = Modifier.height(20.dp)) // Espacio entre botones y el texto final

            // Aquí agregamos el texto de diseño al final
            Text(
                text = "Design by Gran Pata", // El texto que deseas poner
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 14.sp, color = Color.Gray),
                modifier = Modifier.padding(top = 20.dp) // Agregamos un poco de espacio antes del texto
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview(apiLevel: Int = 33) {
    val navController = rememberNavController() // Creamos un NavController simulado
    StartScreen(navController = navController) // Pasamos el NavController simulado
}
