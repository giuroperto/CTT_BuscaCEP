package com.ctt.buscacep.repository

import androidx.lifecycle.MutableLiveData
import com.ctt.buscacep.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

    fun searchAddress(state: String, city: String, street: String, ) : MutableLiveData<StateResponse<Address>> {
        val addressLiveData = MutableLiveData<StateResponse<Address>>()

        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/")

        val service = retrofitClient.create(AddressService::class.java)

        val call = service.fetchAddress(state = state, city = city, street = street)

        call.enqueue(
                object : Callback<Address> {
                    override fun onResponse(call: Call<Address>, response: Response<Address>) {
                        if (response.isSuccessful && response.body() != null) {
                            response.body()?.let {
                                addressLiveData.value = StateSuccess(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Address>, t: Throwable) {
                        addressLiveData.value = StateError(t)
                    }
                }
        )

        return addressLiveData

    }

}
