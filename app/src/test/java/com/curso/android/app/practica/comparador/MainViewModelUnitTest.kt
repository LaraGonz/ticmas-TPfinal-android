package com.curso.android.app.practica.comparador

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.curso.android.app.practica.comparador.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 *
 * Este test es para saber si cuando arranca, la variable resultadoComparacion esta en "Esperando contraseña..."
 */

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue() = runTest {
        val value = viewModel.resultadoComparacion.value?.resultadoComparacion
        assertEquals("Esperando contraseña...", value)
    }

    /**
     * Hasta acá testió lo de resultadoComparacion = "Esperando contraseña..."
     * y ahora abajo va a testear otras cosas
     */

    @Test //para saber si funciona bien cuando tiene valores diferentes
    fun mainViewModel_TestCompararTextosDiferentes() = runTest {
        launch {
            viewModel.compararTextos("contraseña","repite tu contraseña")
        }
        advanceUntilIdle()  /**Esta funcion hace que tome el valor después de incrementarlo, xq sino lo toma antes el prog bldo*/
        val value = viewModel.resultadoComparacion.value?.resultadoComparacion
        assertEquals("Diferentes", value)
    }

    @Test  //para saber si funciona bien cuando tiene valores iguales.
    fun mainViewModel_TestCompararTextosIguales() = runTest {
        launch {
            viewModel.compararTextos("contraseña","contraseña")
        }
        advanceUntilIdle()  /**Esta funcion hace que tome el valor después de incrementarlo, xq sino lo toma antes el prog bldo*/
    val value = viewModel.resultadoComparacion.value?.resultadoComparacion
        assertEquals("Iguales", value)
    }

}