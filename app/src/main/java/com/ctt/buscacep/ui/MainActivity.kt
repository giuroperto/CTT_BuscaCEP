package com.ctt.buscacep.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ctt.buscacep.R
import com.ctt.buscacep.model.*

class MainActivity : AppCompatActivity() {

    private lateinit var cepField: EditText
    private lateinit var addressField: EditText
    private lateinit var cityField: EditText
    private lateinit var stateField: EditText
    private lateinit var formBtn: Button
    private lateinit var resultTxt: TextView

    private val CEPViewModel = CEPViewModel()
    private val addressViewModel = AddressViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cepField = findViewById(R.id.edtCEP)
        addressField = findViewById(R.id.edtAddress)
        cityField = findViewById(R.id.edtCity)
        stateField = findViewById(R.id.edtState)
        formBtn = findViewById(R.id.btnBuscarCEP)
        resultTxt = findViewById(R.id.txtCEPResponse)

        formBtn.setOnClickListener {
            val cep = cepField.text.toString()
            val address = addressField.text.toString()
            val city = cityField.text.toString()
            val state = stateField.text.toString()

            if (cep.isNotEmpty()) {
                searchCEP(cep)
            } else {
                if (address.isNotEmpty() && city.isNotEmpty() && state.isNotEmpty()) {
                    searchAddress(address, city, state)
                } else {
                    cepField.error = "Ou digite um CEP valido!"
                    addressField.error = "Ou digite um endereco valido..."
                    cityField.error = "E uma cidade valida..."
                    stateField.error = "E um estado valido!"
                }
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

    fun searchAddress(address: String, city: String, state: String) {
        addressViewModel.fetchAddress(address, city, state).observe(
                this,
                object : Observer<StateResponse<Address>> {
                    override fun onChanged(t: StateResponse<Address>?) {
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