package com.example.countriesapi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.countriesapi.R
import com.example.countriesapi.model.ScoreEntry
import com.example.countriesapi.ui.viewmodels.ScoreViewModel
import com.example.countriesapi.ui.viewmodels.ScoreViewModelFactory

@Composable
fun SaveScoreScreen(finalScore: Int, navController: NavController) {
    val context = LocalContext.current
    val viewModel: ScoreViewModel = viewModel(factory = ScoreViewModelFactory(context))
    var nickname by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.txt_terminado),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(stringResource(id = R.string.txt_puntaje_final)+" $finalScore")
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text(stringResource(id = R.string.txt_nick)) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (nickname.isNotBlank()) {
                viewModel.saveScore(ScoreEntry(nickname, finalScore))
                navController.navigate("high_scores")
            }
        }) {
            Text(stringResource(id = R.string.btn_guardar))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SaveScoreScreenPreview() {
    // Simulamos un NavController para el preview
    val navController = rememberNavController()

    // Se pasa un puntaje fijo para la previsualización
    SaveScoreScreen(finalScore = 250, navController = navController)
}

