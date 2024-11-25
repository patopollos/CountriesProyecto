package com.example.countriesapi

import com.example.countriesapi.ui.viewmodels.CountryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class CountryViewModelTest {

    private lateinit var viewModel: CountryViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)  // Establece el Dispatcher principal para las pruebas
        viewModel = CountryViewModel() // Instancia tu ViewModel
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()  // Resetea el Main dispatcher después de las pruebas
    }

    @Test
    fun testCheckAnswerIncorrect() = runTest {
        // Simula la respuesta incorrecta
        val selectedAnswer = "Madrid"
        viewModel.checkAnswer(selectedAnswer)

        // Verifica que el puntaje no haya cambiado
        val triviaState = viewModel.triviaUiState.value
        assert(triviaState.currentScore == 0)

        // Verifica que el número de respuestas incorrectas haya aumentado
        assert(triviaState.incorrectAnswers == 1)
    }


}
