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

//        como eh dado vivo, tem que observar, pois so a partir dai vai saber se deu tudo certo
        viewModel.buscarCEP(cep).observe(
//                paramentros: dono/contexto e o que vai fazer (callback)
                this,
                object : Observer<StateResponse<CEP>> {
                    override fun onChanged(t: StateResponse<CEP>?) {
                        t?.let {
                            when(t) {
//                                quando T for uma classe do tipo sucesso, faz isso e erro faz aquilo
//                                  is eh para verificacao de classes
                                is StateSuccess -> respostaCEP.text = t.data.toString()
                                is StateError -> respostaCEP.text = "Opa, aconteceu alguma coisa!"
                            }
                        }
                    }
//                     quando o dado sofre alteracao
//                    t -> parametros genericos
//                    override fun onChanged(t: CEP?) {
//                        t?.let {
////                             so tem o papel de exibir os dados no final, nao faz mais nada -> talvez apenas alguma validacao se o campo esta vazio, mas so
//                            respostaCEP.text = it.toString()
//                        }
//                    }
                }
        )

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

// tudo que eh externo ao seu app -> PERMISSIONAMENTO
//api -> requisicao pela internet -> nao da pra usar assim -> precisa de permissao no MANIFEST

//se retornasse uma imagem -> imageView = setImageURL("http")



// trabalhamos na clean code com arquiteturas genericas mas escalaveis