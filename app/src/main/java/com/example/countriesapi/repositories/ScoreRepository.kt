package com.example.countriesapi.repositories

// ScoreRepository.kt

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.countriesapi.model.ScoreEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensión de Contexto para DataStore
private val Context.dataStore by preferencesDataStore(name = "scores")

class ScoreRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        // Clave para almacenar la lista de puntajes
        private val SCORE_LIST_KEY = stringPreferencesKey("score_list")
    }

    // Guardar un nuevo puntaje en la lista de puntajes
    suspend fun saveScore(scoreEntry: ScoreEntry) {
        dataStore.edit { preferences ->
            // Obtener la lista de puntajes actual
            val currentScores = preferences[SCORE_LIST_KEY]?.let { decodeScores(it) } ?: emptyList()

            // Añadir el nuevo puntaje y ordenar para mantener los 10 más altos
            val updatedScores = (currentScores + scoreEntry)
                .sortedByDescending { it.points }
                .take(10)

            // Guardar la lista actualizada como una cadena codificada
            preferences[SCORE_LIST_KEY] = encodeScores(updatedScores)
        }
    }

    // Recuperar la lista de puntajes desde DataStore
    fun getScores(): Flow<List<ScoreEntry>> {
        return dataStore.data.map { preferences ->
            preferences[SCORE_LIST_KEY]?.let { decodeScores(it) } ?: emptyList()
        }
    }

    // Codificar la lista de puntajes en una cadena para almacenar en DataStore
    private fun encodeScores(scores: List<ScoreEntry>): String {
        return scores.joinToString(";") { "${it.nickname},${it.points}" }
    }

    // Decodificar la cadena de puntajes en una lista de ScoreEntry
    private fun decodeScores(encodedScores: String): List<ScoreEntry> {
        return encodedScores.split(";").mapNotNull {
            val parts = it.split(",")
            if (parts.size == 2) {
                ScoreEntry(parts[0], parts[1].toInt())
            } else null
        }
    }
}
