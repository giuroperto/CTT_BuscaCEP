package com.ctt.buscacep

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {
    companion object {
//        para configurar o retrofit -> criar funcao estatica

        fun RetrofitConfig(path: String) : Retrofit {
//            aqui eh um retrofit builder, nao eh o retrofit ainda -> serve apenas para configurar o retrofit
            return Retrofit.Builder()
//                    endereco da API
//                    https://viacep.com.br/ws/01001000/json/
//                    a base que nunca muda + cep e json que podem mudar
//                    URL base da requisicao (api)
                .baseUrl(path)
//                    como tratar os dados vindos e vai fazer a conversao
//                    serializacao e desserializacao -> conversao e desconversao de xml para json por exemplo
//                    Responsavel por manipular JSON
//                    vai mudar de acordo com a biblioteca utilizada
                .addConverterFactory(GsonConverterFactory.create())
//            para construir ele de fato -> retrofit -> agora pode utilizar ele no codigo
                .build()
        }
    }
}

// boas praticas pois se refere a rede e nao ao nome da library -> pode mudar

// se for trabalhar com outras APIs nao precisa criar outro -> tirar hardcode e colocar como parametro

//class Network {
//    companion object {
////        para configurar o retrofit -> criar funcao estatica
//
//        fun RetrofitConfig() : Retrofit {
////            aqui eh um retrofit builder, nao eh o retrofit ainda -> serve apenas para configurar o retrofit
//            return Retrofit.Builder()
////                    endereco da API
////                    https://viacep.com.br/ws/01001000/json/
////                    a base que nunca muda + cep e json que podem mudar
////                    URL base da requisicao (api)
//                .baseUrl("https://viacep.com.br/ws/")
////                    como tratar os dados vindos e vai fazer a conversao
////                    serializacao e desserializacao -> conversao e desconversao de xml para json por exemplo
////                    Responsavel por manipular JSON
////                    vai mudar de acordo com a biblioteca utilizada
//                .addConverterFactory(GsonConverterFactory.create())
////            para construir ele de fato -> retrofit -> agora pode utilizar ele no codigo
//                .build()
//        }
//    }
//}