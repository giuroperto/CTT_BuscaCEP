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
}


// TODO: 09/04/2021 testar breakpoint condicional 
// TODO: 09/04/2021 testar breakpoint com alteracao de variavel