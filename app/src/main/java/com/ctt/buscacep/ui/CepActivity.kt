package com.ctt.buscacep.ui

import android.graphics.Color
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

class CepActivity : AppCompatActivity() {

    private lateinit var cepField: EditText
    private lateinit var resultTxt: TextView
    private val CEPViewModel = CEPViewModel()

    private lateinit var formBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cep)

        cepField = findViewById(R.id.edtCEP)
        resultTxt = findViewById(R.id.txtCEPResponse)
        formBtn = findViewById(R.id.btnBuscarCEP)

        formBtn.setOnClickListener {
            val cep = cepField.text.toString()

            if (cep.isNotEmpty()) {
                searchCEP(cep)
            } else {
                cepField.error = "Digite um CEP valido!"
            }
        }
    }

    fun searchCEP(cep: String) {

        CEPViewModel.buscarCEP(cep).observe(
                this,
                object : Observer<StateResponse<CEP>> {
                    override fun onChanged(t: StateResponse<CEP>?) {
                        t?.let {
                            when(t) {
                                is StateSuccess -> resultTxt.text = t.data.toString()
                                is StateError -> resultTxt.text = "Opa, aconteceu alguma coisa!"
                            }
                        }
                    }
                }
        )

    }
}