package com.ctt.buscacep.model

import com.google.gson.annotations.SerializedName

data class CEP(
    @SerializedName("logradouro")
    val rua: String,
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("localidade")
    val cidade: String,
    @SerializedName("uf")
    val uf: String,
    val moroAqui: Boolean
    ) {
    override fun toString(): String {
        return "Rua: $rua\nBairro: $bairro\nCidade: $cidade\nEstado: $uf"
    }
}
