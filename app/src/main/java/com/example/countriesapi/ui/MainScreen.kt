package com.example.countriesapi.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.countriesapi.R
import com.example.countriesapi.ui.navigation.Header
import com.example.countriesapi.ui.screens.CountryTriviaScreen
import com.example.countriesapi.ui.screens.HighScoresScreen
import com.example.countriesapi.ui.screens.SaveScoreScreen
import com.example.countriesapi.ui.screens.StartScreen


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val title = remember { mutableStateOf("Trivia de Países") }

    Scaffold(
        topBar = { Header(title = title.value) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "start",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("start") {
                title.value = stringResource(id = R.string.titulo_app)
                StartScreen(navController)
            }
            composable("trivia") {
                title.value = stringResource(id = R.string.titulo_app)
                CountryTriviaScreen(navController)
            }
            composable("high_scores") {
                title.value = stringResource(id = R.string.btn_puntajes)
                HighScoresScreen(navController)
            }
            composable("save_score/{finalScore}") { backStackEntry ->
                title.value = stringResource(id = R.string.btn_guardar)
                val finalScore = backStackEntry.arguments?.getString("finalScore")?.toInt() ?: 0
                SaveScoreScreen(finalScore = finalScore, navController = navController)
            }

        }
    }
}
/*
@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.titulo_app),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Button(
            onClick = { navController.navigate("trivia") },
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(stringResource(id = R.string.btn_inicia_juego))
        }

        Button(
            onClick = { navController.navigate("high_scores") }
        ) {
            Text(stringResource(id = R.string.txt_puntajes_titulo))
        }
    }
}*/