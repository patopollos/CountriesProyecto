package com.example.countriesapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesapi.model.Country
import com.example.countriesapi.repositories.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class TriviaUiState(
    val countryName: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: String = "",
    val selectedAnswer: String? = null,
    val isAnswerCorrect: Boolean? = null,
    val currentScore: Int = 0,
    val questionNumber: Int = 1,
    val totalQuestions: Int = 20,
    val gameFinished: Boolean = false,
    val correctAnswers: Int = 0, // Respuestas correctas
    val incorrectAnswers: Int = 0, // Respuestas incorrectas
    val currentImage: String = ""
)

sealed class CountryAddUiState {
    object Idle : CountryAddUiState()
    object Loading : CountryAddUiState()
    object Success : CountryAddUiState()
    data class Error(val message: String) : CountryAddUiState()
}

class CountryViewModel(private val repository: CountryRepository = CountryRepository()) : ViewModel() {
    private val _triviaUiState = MutableStateFlow(TriviaUiState())
    val triviaUiState: StateFlow<TriviaUiState> = _triviaUiState

    init {
        loadNewQuestion()
    }

    // Función para cargar una nueva pregunta
    private fun loadNewQuestion() {
        viewModelScope.launch {
            val countries = repository.getCountries() // Obtiene la lista de países
            val randomCountry = countries.random() // Selecciona un país aleatorio para la pregunta

            // Genera opciones de respuesta
            val options = generateOptions(randomCountry, countries)

            _triviaUiState.value = _triviaUiState.value.copy(
                countryName = randomCountry.name,
                options = options,
                correctAnswer = randomCountry.capital,
                currentScore = _triviaUiState.value.currentScore,
                currentImage = randomCountry.image

            )
        }
    }

    // Función para verificar si la respuesta es correcta y actualizar el puntaje
    fun checkAnswer(selectedOption: String) {
        // Verificamos si la respuesta es correcta
        val isCorrect = selectedOption == _triviaUiState.value.correctAnswer

        // Incrementar el puntaje según si la respuesta es correcta
        val updatedScore = if (isCorrect) _triviaUiState.value.currentScore + 1 else _triviaUiState.value.currentScore

        // Contadores de respuestas correctas e incorrectas
        val updatedCorrectAnswers = if (isCorrect) _triviaUiState.value.correctAnswers + 1 else _triviaUiState.value.correctAnswers
        val updatedIncorrectAnswers = if (!isCorrect) _triviaUiState.value.incorrectAnswers + 1 else _triviaUiState.value.incorrectAnswers


        val nextQuestionNumber = _triviaUiState.value.questionNumber + 1

        // Verificar si el juego terminó: 7 respuestas correctas o 4 incorrectas
        val isGameFinished = updatedCorrectAnswers == 7 || updatedIncorrectAnswers == 4

        //val isGameFinished = nextQuestionNumber > _triviaUiState.value.totalQuestions
        _triviaUiState.value = _triviaUiState.value.copy(
            selectedAnswer = selectedOption,
            isAnswerCorrect = isCorrect,
            currentScore = updatedScore,
            correctAnswers = updatedCorrectAnswers,
            incorrectAnswers = updatedIncorrectAnswers,
            questionNumber = nextQuestionNumber,
            gameFinished = isGameFinished
        )

        if (!isGameFinished) {
            // Cargar la siguiente pregunta
            viewModelScope.launch {
                //kotlinx.coroutines.delay(500)
                loadNewQuestion()
            }
        }

        // Carga una nueva pregunta
        viewModelScope.launch {
            //kotlinx.coroutines.delay(500) // Pausa para mostrar el resultado al usuario
            loadNewQuestion() // Cargar la siguiente pregunta
        }


    }

    // Genera opciones de respuesta mezcladas
    private fun generateOptions(correctCountry: Country, countries: List<Country>): List<String> {
        val incorrectOptions = countries.filter { it != correctCountry }
            .shuffled()
            .take(3)
            .map { it.capital }

        return (incorrectOptions + correctCountry.capital).shuffled()
    }
}