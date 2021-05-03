package com.ctt.buscacep.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ctt.buscacep.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressRepository {

    fun searchAddress(state: String, city: String, street: String, ) : MutableLiveData<StateResponse<AddressList>> {
        val addressLiveData = MutableLiveData<StateResponse<AddressList>>()

        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/")

        val service = retrofitClient.create(AddressService::class.java)

        val call = service.fetchAddress(state = state, city = city, street = street)

        Log.d("TRACKER", "inside serach repository")
        Log.d("TRACKER", "$state, $city, $street")
        Log.d("TRACKER", call.toString())

        call.enqueue(
                object : Callback<AddressList> {
                    override fun onResponse(call: Call<AddressList>, response: Response<AddressList>) {
                        Log.d("TRACKER", "inside response repository")
                        Log.d("TRACKER", response.body().toString())
                        Log.d("TRACKER", response.isSuccessful.toString())

                        if (response.isSuccessful && response.body() != null) {

                            Log.d("TRACKER", "inside success repository")

                            response.body()?.let {
                                Log.d("API RESPONSE", it.toString())
                                addressLiveData.value = StateSuccess(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<AddressList>, t: Throwable) {
                        Log.d("TRACKER", "inside failure repository")
                        addressLiveData.value = StateError(t)
                    }
                }
        )

        Log.d("TRACKER", "before return repository")
        println(addressLiveData.value)
        return addressLiveData

    }

}
