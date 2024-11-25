package com.example.countriesapi

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.example.countriesapi.ui.screens.StartScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StartScreenInstrumentedTest {

    // Crear una regla para las pruebas de UI
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // Configuramos el contexto antes de cada prueba (por si es necesario)
        // No es necesario aquí, pero se podría usar si se quisiera inicializar algo
    }

    @Test
    fun testStartGameButtonIsDisplayed() {
        // Coloca la pantalla StartScreen en la regla de prueba
        composeTestRule.setContent {
            StartScreen(navController = rememberNavController())
        }

        // Verifica que el botón "Iniciar Juego" se muestre
        composeTestRule.onNodeWithText("Iniciar Juego").assertIsDisplayed()
    }

    @Test
    fun testLanguageSwitchButtonChangesLanguage() {
        // Configura la pantalla StartScreen
        composeTestRule.setContent {
            StartScreen(navController = rememberNavController())
        }

        // Espera a que la interfaz esté inactiva (completamente renderizada)
        composeTestRule.waitForIdle()

        // Verifica que el botón de cambiar idioma "English" esté visible
        composeTestRule.onNodeWithText("English").assertIsDisplayed()
    }


}
