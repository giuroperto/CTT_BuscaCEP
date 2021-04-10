package com.ctt.buscacep.repository

import androidx.lifecycle.MutableLiveData
import com.ctt.buscacep.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

    fun searchAddress(state: String, city: String, street: String, ) : MutableLiveData<StateResponse<Array<Address>>> {
        val addressLiveData = MutableLiveData<StateResponse<Array<Address>>>()

        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/")

        val service = retrofitClient.create(AddressService::class.java)

        val call = service.fetchAddress(state = state, city = city, street = street)

        call.enqueue(
                object : Callback<Array<Address>> {

                    override fun onResponse(call: Call<Array<Address>>, response: Response<Array<Address>>) {
                        if (response.isSuccessful && response.body() != null) {
                            response.body()?.let {
                                addressLiveData.value = StateSuccess(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<Array<Address>>, t: Throwable) {
                        addressLiveData.value = StateError(t)
                    }


                }
        )

        println(addressLiveData.value)
        return addressLiveData

    }

}
