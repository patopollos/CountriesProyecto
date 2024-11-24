package com.example.countriesapi.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapi.model.ScoreEntry
import com.example.countriesapi.repositories.ScoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScoreViewModel(context: Context) : ViewModel() {

    private val scoreRepository = ScoreRepository(context)

    // Flujo que emite la lista de puntajes altos
    val highScores: Flow<List<ScoreEntry>> = scoreRepository.getScores().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // Función para guardar un nuevo puntaje
    fun saveScore(scoreEntry: ScoreEntry) {
        viewModelScope.launch {
            scoreRepository.saveScore(scoreEntry)
        }
    }
}
