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

class CEPRepository {

    fun buscarViaCEP(cepUsuario: String) : MutableLiveData<StateResponse<CEP>> {
        val cepLiveData = MutableLiveData<StateResponse<CEP>>()

        val retrofitClient = Network.RetrofitConfig("https://viacep.com.br/ws/")

        val servico = retrofitClient.create(CEPService::class.java)

        val chamada = servico.buscarCEP(cepUsuario)

        chamada.enqueue(
                object : Callback<CEP> {
                    override fun onResponse(call: Call<CEP>, response: Response<CEP>) {

                        if(response.isSuccessful && response.body() != null) {
                            response.body()?.let {
                                cepLiveData.value = StateSuccess(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<CEP>, t: Throwable) {
                        cepLiveData.value = StateError(t)
                    }
                }
        )

        return cepLiveData

    }
}