package com.ctt.buscacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ctt.buscacep.model.CEP
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/01001000/json/")
        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/")

//        val cepExemplo = CEP(rua = "Rua Qualquer", bairro = "Meu bairro", cidade = "Minha cidade", uf = "Meu estado", moroAqui = true)
//        cepExemplo.toString() // <- nosso TOSTRING personalizado

//        Criando servico que contera as rotas (final da URL) e atribuindo ela a configuracao do meu retrofit
//        crio as rotas que ele vai utilizar -> todas que estao no cepservice
//        "https://viacep.com.br/ws/ + {cepInserido}/json/"
//        precisa receber antes da app rodar -> oq tem antes -> arquivo java
        val servico = retrofitClient.create(CEPService::class.java)

//        agora que vai ter a chamada
        val chamada = servico.buscarCEP(cep)

//        e o retorno?

//        metodo retrofit enqueue que pode ser nulo
//        Responsavel por realizar a chamada
//        1) .execute tambem podemos utilizar -> realiza a chamada de maneira SINCRONA
//        2) .enqueue -> realiza a chamada de maneira ASSINCRONA

//        val callback = Callback<CEP>()


//        Call<CEP> = Preparacao de uma chamada do tipo CEP
//        Callback<CEP> = Fazer a chamada do tipo CEP, que me trara uma resposta do tipo CEP
//        Response<CEP> = Resposta da API do tipo CEP

//        SEMPRE IMPORTAR TUDO DO RETROFIT

        chamada.enqueue(
// callback -> call eh a chamada, callback eh o retorno
//        instanciar um callback do tipo CEP pois vem do service
//            pegue um objeto do tipo callback -> objeto abstrato e precisamos implementar os membros dele

//            precisamos entender a resposta -> callback -> 2 respostas (response/failure)
            object : Callback<CEP> {
                override fun onResponse(call: Call<CEP>, response: Response<CEP>) {
//                    quando tiver resposta -> faca
//                    pega a resposta pelo body
//                    da pra fazer let caso a mensagem seja nula
//                    response.body().let {
//                        it.
//                    }

//                    respostaCEP.text = response.body().toString()
                    val endereco = response.body()?.toString()

//                    if (endereco.isNotEmpty()) {
//                        respostaCEP.text = endereco
//                    } else {
//                        respostaCEP.text = "Opa, nao foi encontrado endereco nenhum"
//                    }
                    endereco?.let {
                        if (it.isNotEmpty()) {
                            respostaCEP.text = endereco
                        } else {
                            respostaCEP.text = "Opa, nao foi encontrado endereco nenhum"
                        }
                    }


//                    response.body().toString() // nao da problema pois se vier nulo a string e nula, diferente de se pegar um atributo direto
                }

                override fun onFailure(call: Call<CEP>, t: Throwable) {
//                    quando tiver erro -> faca
//                    falha de conexao (timeout)
//                    erro de rota
//                    FALHA DE COMUNICACAO
                    respostaCEP.text = "Opa, houve erro na comunicacao, tente novamente mais tarde!"
                }

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