package com.ctt.buscacep.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.ctt.buscacep.R
import com.ctt.buscacep.model.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var cepField: EditText
    private lateinit var addressField: EditText
    private lateinit var cityField: EditText
//    private lateinit var stateField: EditText
    private lateinit var formBtn: Button
    private lateinit var resultTxt: TextView
    private lateinit var stateSpn: Spinner

    private val CEPViewModel = CEPViewModel()
    private val addressViewModel = AddressViewModel()

    private lateinit var selectedState: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stateList = resources.getStringArray(R.array.state_acronym)

        cepField = findViewById(R.id.edtCEP)
        addressField = findViewById(R.id.edtAddress)
        cityField = findViewById(R.id.edtCity)
//        stateField = findViewById(R.id.spnState)
        formBtn = findViewById(R.id.btnBuscarCEP)
        resultTxt = findViewById(R.id.txtCEPResponse)
        stateSpn = findViewById(R.id.spnState)

        if (stateSpn != null) {
            val spnAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stateList)
            stateSpn.adapter = spnAdapter

            stateSpn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    selectedState = stateList[position]
//                    Toast.makeText(this@MainActivity, getString(R.string.selected_item) + " " + stateList[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

        }

        formBtn.setOnClickListener {
            val cep = cepField.text.toString()
            val address = addressField.text.toString()
            val city = cityField.text.toString()

            if (cep.isNotEmpty()) {
                searchCEP(cep)
            } else {
                if (address.isNotEmpty() && city.isNotEmpty() && selectedState.isNotEmpty()) {
                    searchAddress(address, city, selectedState)
                } else {
                    cepField.error = "Ou digite um CEP valido!"
                    addressField.error = "Ou digite um endereco valido..."
                    cityField.error = "E uma cidade valida..."

                    if (selectedState == resources.getStringArray(R.array.state_acronym)[0]) {
                        val errorText: TextView? = stateSpn.selectedView as TextView?
                        errorText?.error = ""
                        errorText?.setTextColor(Color.RED)
                        errorText?.text = "Selecione um estado!"
                    }
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


// TODO: 09/04/2021 testar breakpoint condicional 
// TODO: 09/04/2021 testar breakpoint com alteracao de variavel 