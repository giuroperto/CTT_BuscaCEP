package com.ctt.buscacep.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.model.StateError
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.model.StateSuccess
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// RESPONSAVEL SOMENTE POR REALIZAR CHAMADAS

class CEPRepository {

    fun buscarViaCEP(cepUsuario: String) : MutableLiveData<StateResponse<CEP>> {
        val cepLiveData = MutableLiveData<StateResponse<CEP>>()

        // todas as chamadas de CEP ficarao aqui

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
        val chamada = servico.buscarCEP(cepUsuario)

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
//                        val endereco = response.body()?.toString()

//                    if (endereco.isNotEmpty()) {
//                        respostaCEP.text = endereco
//                    } else {
//                        respostaCEP.text = "Opa, nao foi encontrado endereco nenhum"
//                    }
//                        endereco?.let {
//                            if (it.isNotEmpty()) {
//                                respostaCEP.text = endereco
//                            } else {
//                                respostaCEP.text = "Opa, nao foi encontrado endereco nenhum"
//                            }
//                        }

//                    response.body().toString() // nao da problema pois se vier nulo a string e nula, diferente de se pegar um atributo direto

                        if(response.isSuccessful && response.body() != null) {
//                             acessa o valor pois se nao retornaria tudo que tem em MutableLiveData
//                              quero indicar o que esta dentro desse dado mutavel -> variavel -> CEP

                            response.body()?.let {
                                cepLiveData.value = StateSuccess(it)
                            }
                        }

                    }

                    override fun onFailure(call: Call<CEP>, t: Throwable) {
//                    quando tiver erro -> faca
//                    falha de conexao (timeout)
//                    erro de rota
//                    FALHA DE COMUNICACAO
//                        respostaCEP.text = "Opa, houve erro na comunicacao, tente novamente mais tarde!"

//                        Log.d("TAG", "Deu ruim!")


//                         se algo der errado, acessa o bd local

//                        CEPLocalRepository().buscarCEPLocal()

                        cepLiveData.value = StateError(t)
                    }

                }
        )

        return cepLiveData

    }

}