package com.curso.android.app.practica.comparador.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.curso.android.app.practica.comparador.model.Comparador
import kotlinx.coroutines.launch


class MainViewModel: ViewModel() {

    val resultadoComparacion: LiveData<Comparador> get() = _resultadoComparacion
    private val _resultadoComparacion = MutableLiveData(Comparador("Esperando contraseña..."))

    fun compararTextos(Texto1: String, Texto2: String) {
        if (Texto1 == Texto2) {
            //"Iguales"
            val comparador = Comparador("Iguales")
            _resultadoComparacion.value = comparador
            println("Lo está respondiendo desde el Model y son iguales")
        } else {
            //"Diferentes"
            val comparador = Comparador("Diferentes")
            _resultadoComparacion.value = comparador
            println("Lo está respondiendo desde el Model y son diferentes")
        }

    }

}
