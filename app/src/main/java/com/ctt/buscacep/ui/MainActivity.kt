package com.ctt.buscacep.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.ctt.buscacep.R
import com.ctt.buscacep.model.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var btnCep: Button
    private lateinit var btnAddress: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCep = findViewById(R.id.btn_cep)
        btnAddress = findViewById(R.id.btn_address)

        btnCep.setOnClickListener {
            val intent = Intent(this, CepActivity::class.java)
            startActivity(intent)
        }

        btnAddress.setOnClickListener {
            val intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
        }
    }

//    fun searchAddress(address: String, city: String, state: String) {
//
////        addressViewModel.fetchAddress(address, city, state).observe(
//        addressViewModel.fetchAddress().observe(
//                this,
//                object : Observer<StateResponse<AddressList>> {
//                    override fun onChanged(t: StateResponse<AddressList>?) {
//                        println("Dentro on changed")
//
//                        t?.let {
//
//                            println("Dentro LET")
//
//                            if (t is StateSuccess) {
//                                Log.d("SUCESSO", t.data.toString())
//                            } else {
//                                Log.e("ERROR", "ERRO")
//                            }
//
//
//                            when(t) {
////                                is StateSuccess ->Log.d("API CALL", t.data.toString())
////                                is StateSuccess -> resultTxt.text = t.data.toString()
//                                is StateError -> resultTxt.text = "Opa, aconteceu alguma coisa!"
//                            }
//                        }
//                    }
//                }
//        )
//    }

}


// TODO: 09/04/2021 testar breakpoint condicional 
// TODO: 09/04/2021 testar breakpoint com alteracao de variavel
// TODO: 10/04/2021 fix request/response from api using address