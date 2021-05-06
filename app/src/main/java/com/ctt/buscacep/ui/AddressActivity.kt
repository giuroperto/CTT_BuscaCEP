package com.ctt.buscacep.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ctt.buscacep.R
import com.ctt.buscacep.model.Address
import com.ctt.buscacep.model.StateError
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.model.StateSuccess

class AddressActivity : AppCompatActivity() {

    private lateinit var addressField: EditText
    private lateinit var cityField: EditText
    private lateinit var formBtn: Button
    private lateinit var stateSpn: Spinner
    private lateinit var rvResults: RecyclerView

    private val addressViewModel = AddressViewModel()

    private lateinit var selectedState: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        val stateList = resources.getStringArray(R.array.state_acronym)

        addressField = findViewById(R.id.edtAddress)
        cityField = findViewById(R.id.edtCity)
        formBtn = findViewById(R.id.btnBuscarCEP)
        rvResults = findViewById(R.id.rv_results)

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
//            val listOfAddresses = addressViewModel.fetchAddress("Abilio", "Sao Paulo", "SP") as List<Address>

            formBtn.setOnClickListener {
                val address = addressField.text.toString()
                val city = cityField.text.toString()

                if (address.isNotEmpty() && city.isNotEmpty() && selectedState.isNotEmpty()) {
                    searchAddress(address, city, selectedState)
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

    fun searchAddress(address: String, city: String, state: String) {

        addressViewModel.fetchAddress(address, city, state).observe(
                this,
                object : Observer<StateResponse<List<Address>>> {
                    override fun onChanged(t: StateResponse<List<Address>>?) {
                        t?.let {

                            if (t is StateSuccess) {
                                val adapter = AddressAdapter(t.data)
                                rvResults.adapter = adapter
                                rvResults.layoutManager = LinearLayoutManager(this@AddressActivity)
//                                Log.d("SUCESSO", t.data.toString())
                            } else {
                                Toast.makeText(this@AddressActivity, "Erro ao buscar endereco!", Toast.LENGTH_SHORT).show()
//                                Log.e("ERROR", "ERRO")
                            }

                        }
                    }
                }
        )
    }
}