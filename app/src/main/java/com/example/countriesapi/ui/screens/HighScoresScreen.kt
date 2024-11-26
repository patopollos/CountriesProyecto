package com.example.countriesapi.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapi.BuildConfig
import com.example.countriesapi.R
import com.example.countriesapi.model.ScoreEntry
import com.example.countriesapi.ui.viewmodels.ScoreViewModel
import com.example.countriesapi.ui.viewmodels.ScoreViewModelFactory


@Composable
fun HighScoresScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: ScoreViewModel = viewModel(factory = ScoreViewModelFactory(context))

    // Obtenemos la lista de puntajes altos desde el ViewModel
    val highScores by viewModel.highScores.collectAsState(emptyList())
    Log.d("AppMode", BuildConfig.APP_MODE)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.txt_puntajes_titulo),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Verificamos si hay puntajes para mostrar
        if (highScores.isNotEmpty()) {
            // Usamos LazyColumn para mostrar la lista de puntajes
            LazyColumn {
                itemsIndexed(highScores) { index, score ->
                    ScoreRow(index + 1, score)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        } else {
            Text(stringResource(id = R.string.txt_no_puntajes))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón para volver al inicio
        Button(onClick = { navController.navigate("start") },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            Text(stringResource(id = R.string.btn_volver),
                style = TextStyle(fontSize = 20.sp) )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {navController.navigate("trivia")},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp)) {
            Text(stringResource(id = R.string.btn_denuevo),
                style = TextStyle(fontSize = 20.sp) )
        }
    }
}

@Composable
fun ScoreRow(position: Int, score: ScoreEntry) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("$position. ${score.nickname}", fontWeight = FontWeight.Medium)
        Text("${score.points} "+ stringResource(id = R.string.txt_puntos), fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun HighScoreScreenPreview() {
    val navController = rememberNavController() // Creamos un NavController simulado
    HighScoresScreen(navController = navController) // Pasamos el NavController simulado
}