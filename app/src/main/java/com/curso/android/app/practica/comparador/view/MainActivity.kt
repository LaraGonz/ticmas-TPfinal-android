package com.curso.android.app.practica.comparador.view

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.curso.android.app.practica.comparador.databinding.ActivityMainBinding
import com.curso.android.app.practica.comparador.view.MainViewModel


class MainActivity() : AppCompatActivity(), Parcelable {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esto es cuando hacen click en comparar.
        binding.compararButton.setOnClickListener {
            val valorTexto1 = binding.Texto1.text.toString()
            val valorTexto2 = binding.Texto2.text.toString()

            mainViewModel.compararTextos(valorTexto1, valorTexto2)
        }

        mainViewModel.resultadoComparacion.observe(this, { comparador ->
            binding.resultado.text = comparador.resultadoComparacion
        })

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}
