package com.example.countriesapi.ui.screens

//import androidx.compose.ui.text.intl.Locale
//import androidx.compose.ui.text.intl.LocaleList

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapi.R

@Composable
fun StartScreen(navController: NavController) {

    val flags = listOf(
        R.drawable.argentina, R.drawable.brasil, R.drawable.chile,
        R.drawable.china, R.drawable.cocos, R.drawable.crica,
        R.drawable.espana, R.drawable.eu, R.drawable.holanda,
        R.drawable.inglaterra, R.drawable.japon, R.drawable.mexico,
        R.drawable.suiza
    )
    val scrollState = rememberScrollState()
    val flagOffset = animateFloatAsState(targetValue = -100f, label = "banner") // Hacemos que las banderas se deslicen fuera de la pantalla

    // Se podría tener un efecto de desplazamiento o transición infinita
    LaunchedEffect(true) {
        // Animación de las banderas
        while (true) {
            scrollState.scrollTo(0) // Se resetea el desplazamiento para que las banderas continúen
            kotlinx.coroutines.delay(2000) // Cambiar de posición cada 2 segundos
        }
    }

    // Contenido de la pantalla de inicio
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Banner animado con banderas
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .offset(x = flagOffset.value.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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
                onClick = { navController.navigate("quiz") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_inicia_juego))
            }

            // Botón para ir a tabla de puntajes
            Button(
                onClick = { navController.navigate("high_scores") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_puntajes))
            }

            // Botón para ir a tabla de puntajes
            Button(
                onClick = { navController.navigate("high_scores") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
            ) {
                Text(text = stringResource(id = R.string.btn_puntajes))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    val navController = rememberNavController() // Creamos un NavController simulado
    StartScreen(navController = navController) // Pasamos el NavController simulado
}