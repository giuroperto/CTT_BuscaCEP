package com.ctt.buscacep.model

import com.google.gson.annotations.SerializedName

data class Address (
        @SerializedName("cep")
        val postcode: String,
        @SerializedName("logradouro")
        val street: String,
        @SerializedName("bairro")
        val neighbourhood: String,
        @SerializedName("localidade")
        val city: String,
        @SerializedName("uf")
        val state: String
) {
    override fun toString(): String {
        return "Rua: $street\nBairro: $neighbourhood\nCidade: $city\nEstado: $state"
    }
}

class AddressList (
        val list: Array<Address>
)