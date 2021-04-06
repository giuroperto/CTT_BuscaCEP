package com.ctt.buscacep.repository

import com.ctt.buscacep.model.Address
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AddressService {

    @GET("{searchState}/{searchCity}/{searchAddress}/json/")

    fun fetchAddress(
            @Path("searchState") state: String,
            @Path("searchCity") city: String,
            @Path("searchAddress") street: String
    ) : Call<Address>
}