package com.ctt.buscacep.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ctt.buscacep.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

//    fun searchAddress(state: String, city: String, street: String, ) : MutableLiveData<StateResponse<List<Address>>> {
//        val addressLiveData = MutableLiveData<StateResponse<List<Address>>>()
    fun searchAddress(state: String, city: String, street: String, ) : MutableLiveData<List<Address>> {
        val addressLiveData = MutableLiveData<List<Address>>()

        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/")

        val service = retrofitClient.create(AddressService::class.java)

        val call = service.fetchAddress(state = state, city = city, street = street) as Call<List<Address>>

        Log.d("TRACKER", "inside serach repository")
        Log.d("TRACKER", "$state, $city, $street")
        Log.d("TRACKER", call.toString())

        call.enqueue(
                object : Callback<List<Address>> {
                    override fun onResponse(call: Call<List<Address>>, response: Response<List<Address>>) {
                        Log.d("TRACKER", "inside response repository")
                        Log.d("TRACKER", response.body().toString())
                        Log.d("TRACKER", response.isSuccessful.toString())

                        if (response.isSuccessful && response.body() != null) {

                            Log.d("TRACKER", "inside success repository")

                            response.body()?.let {
                                Log.d("API RESPONSE", it.toString())
//                                addressLiveData.value = StateSuccess(it)
                                addressLiveData.value = listOf(Address("00000-000", "Abilio", "Paraiso", "Sao Paulo","SP"))
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Address>>, t: Throwable) {
                        Log.d("TRACKER", "inside failure repository")
//                        addressLiveData.value = StateError(t)
                        addressLiveData.value = listOf(Address("11111-111", "Abilio", "Paraiso", "Sao Paulo","SP"))
                    }
                }
        )

        Log.d("TRACKER", "before return repository")
        println(addressLiveData.value)
        return addressLiveData

    }

}
