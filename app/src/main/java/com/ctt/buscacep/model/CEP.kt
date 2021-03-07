package com.ctt.buscacep.model

import com.google.gson.annotations.SerializedName

data class CEP(
//    para usar os nomes da api, nao precisa de funcao para converter
//    nome serializado do json -> resultado da api
    @SerializedName("logradouro")
    val rua: String,
//    mesmo os nomes iguais tem que ter a marcacao, pois a variavel so vale em Kotlin, aqui que cria essa relacao pois sao provenientes de lugares diferentes
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("localidade")
    val cidade: String,
    @SerializedName("estado")
//    com o arroba estamos marcando, marcamos que era o nome original
//    @Deprecated("nao eh cep")
    val uf: String,
//    da para misturar variaveis que vem da api e outras criadas
    val moroAqui: Boolean
    ) {
//    sobrecarga de metodo
    override fun toString(): String {
//        podemos sobrescrever metodos para retornar da maneira que queremos
        return "Rua: $rua\nBairro: $bairro\nCidade: $cidade\nEstado: $uf"
    }
}

//json em Kotlin eh classe -> entao temos que criar o modelo/classe
// dados que podemos pegar da API que nos interessam