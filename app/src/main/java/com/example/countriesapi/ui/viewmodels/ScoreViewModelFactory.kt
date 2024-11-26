package com.example.countriesapi.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Clase Factory para crear una instancia de [ScoreViewModel] con un contexto personalizado.
 *
 * Esta clase implementa [ViewModelProvider.Factory] para crear objetos de [ScoreViewModel]
 * ya que [ScoreViewModel] requiere un [Context] para su inicialización.
 * Usando esta fábrica, podemos pasar un [Context] al crear el [ScoreViewModel]
 * en lugar de depender del mecanismo de creación predeterminado de ViewModel, que no
 * permite pasar parámetros.
 */

class ScoreViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    /**
     * Crea una nueva instancia de [ScoreViewModel] si la clase de ViewModel solicitada
     * coincide con el tipo de [ScoreViewModel]. El contexto se pasa al ViewModel
     * para su inicialización.
     *
     * @param modelClass La clase del [ViewModel] que se desea crear.
     * @return Una nueva instancia de [ScoreViewModel].
     * @throws IllegalArgumentException Si el [modelClass] no es de tipo [ScoreViewModel].
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verificamos si la clase de ViewModel solicitada es ScoreViewModel
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            // Suprimimos la advertencia de conversión de tipos ya que estamos seguros del tipo
            @Suppress("UNCHECKED_CAST")
            // Creamos y retornamos el ScoreViewModel, pasando el contexto
            return ScoreViewModel(context) as T
        }
        // Si la clase de ViewModel no es de tipo ScoreViewModel, lanzamos una excepción
        throw IllegalArgumentException("Clase ViewModel Desconocida")
    }
}