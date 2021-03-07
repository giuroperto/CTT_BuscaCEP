package com.ctt.buscacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var campoCEP: EditText
    private lateinit var botaoCEP: Button
    private lateinit var respostaCEP: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        arquivo gerado de XML total
//        R.java
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
//        Iniciar requisicao a API para buscar CEP

        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/01001000/json/")
    }

}

// nao tem como fazer requisicao de maneira nativa -> precisa de auxilio externo -> biblioteca
//  nao vem no sdk -> dependencia externa
// a biblioteca mais famosa para desenvolvimento Android: RETROFIT

// 1. add no build gradle a dependencia -> graddle do app/modulo
// implementation 'com.squareup.retrofit2:converter-gson:2.8.1'

// 2. json eh js e kotlin nao entende json
// outra biblioteca para que o codigo entenda o json -> conversor
// retrofit tem a opcao de converter json
// implementation 'com.squareup.retrofit2:retrofit:2.9.0'

// construir/inicializar o retrofit