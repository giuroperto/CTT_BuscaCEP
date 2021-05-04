package com.ctt.buscacep.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.ctt.buscacep.R

class AddressActivity : AppCompatActivity() {

    private lateinit var addressField: EditText
    private lateinit var cityField: EditText
    private lateinit var formBtn: Button
    private lateinit var stateSpn: Spinner

    private val addressViewModel = AddressViewModel()

    private lateinit var selectedState: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val stateList = resources.getStringArray(R.array.state_acronym)

        addressField = findViewById(R.id.edtAddress)
        cityField = findViewById(R.id.edtCity)
        formBtn = findViewById(R.id.btnBuscarCEP)

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

            //         TODO: 11/04/2021 to delete after test
//            addressViewModel.fetchAddress()
            addressViewModel.fetchAddress("Abilio", "Sao Paulo", "SP")

            formBtn.setOnClickListener {
                val address = addressField.text.toString()
                val city = cityField.text.toString()

                if (address.isNotEmpty() && city.isNotEmpty() && selectedState.isNotEmpty()) {
//                    searchAddress(address, city, selectedState)
                } else {
//                    cepField.error = "Ou digite um CEP valido!"
                }

                if (address.isEmpty()) {
                    addressField.error = "Ou digite um endereco valido..."
                }

                if (city.isEmpty()) {
                    cityField.error = "E uma cidade valida..."
                }

                if (selectedState == resources.getStringArray(R.array.state_acronym)[0]) {
                    val errorText: TextView? = stateSpn.selectedView as TextView?
                    errorText?.error = ""
                    errorText?.setTextColor(Color.RED)
                    errorText?.text = "Selecione um estado!"
                }
            }
    }
}