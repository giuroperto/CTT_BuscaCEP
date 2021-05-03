package com.ctt.buscacep.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctt.buscacep.model.Address
import com.ctt.buscacep.model.AddressList
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.repository.AddressRepository

class AddressViewModel(
        private val addressRepository: AddressRepository = AddressRepository()
) : ViewModel() {
    private lateinit var AddressLiveData: MutableLiveData<StateResponse<AddressList>>

    fun fetchAddress() : MutableLiveData<StateResponse<AddressList>> {
//    fun fetchAddress(inputAddress: String, inputCity: String, inputState: String) : MutableLiveData<StateResponse<AddressList>> {

//        val adjustedAddress = inputAddress.replace(" ", "%20").toLowerCase()
//        println(adjustedAddress)

//        val adjustedCity = inputCity.replace(" ", "%20").toLowerCase()
//        println(adjustedCity)

//        AddressLiveData = addressRepository.searchAddress(state = inputState, city = adjustedCity, street = adjustedAddress)
        AddressLiveData = addressRepository.searchAddress(state = "SP", city = "sao%20paulo", street = "abilio")

        Log.e("error","RESPOSTA API")
        Log.e("error", AddressLiveData.value.toString())
        return AddressLiveData
    }

}

// TODO: 06/04/2021 create enum for states
// TODO: 10/04/2021 link enum to spinner array
// TODO: 06/04/2021 adjust result fun to loop through array