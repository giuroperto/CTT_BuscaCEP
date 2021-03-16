package com.ctt.buscacep.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ctt.buscacep.R
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.model.StateError
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.model.StateSuccess

class MainActivity : AppCompatActivity() {

    private lateinit var campoCEP: EditText
    private lateinit var botaoCEP: Button
    private lateinit var respostaCEP: TextView

    private val viewModel = MainActivityViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        campoCEP = findViewById(R.id.edtCEP)
        botaoCEP = findViewById(R.id.btnBuscarCEP)
        respostaCEP = findViewById(R.id.txtCEPResponse)

        botaoCEP.setOnClickListener {
            val cep = campoCEP.text.toString()

            if (cep.isNotEmpty()) {
                buscarCEP(cep)
            } else {
                campoCEP.error = "Digite um CEP valido!"
            }
        }
    }

    fun buscarCEP(cep: String) {

        viewModel.buscarCEP(cep).observe(
                this,
                object : Observer<StateResponse<CEP>> {
                    override fun onChanged(t: StateResponse<CEP>?) {
                        t?.let {
                            when(t) {
                                is StateSuccess -> respostaCEP.text = t.data.toString()
                                is StateError -> respostaCEP.text = "Opa, aconteceu alguma coisa!"
                            }
                        }
                    }
                }
        )

    }

}