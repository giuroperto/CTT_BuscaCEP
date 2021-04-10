package com.ctt.buscacep.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctt.buscacep.model.Address
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.repository.AddressRepository

class AddressViewModel(
        private val addressRepository: AddressRepository = AddressRepository()
) : ViewModel() {
    private lateinit var AddressLiveData: MutableLiveData<StateResponse<Array<Address>>>

    fun fetchAddress(inputAddress: String, inputCity: String, inputState: String) : MutableLiveData<StateResponse<Array<Address>>> {

        val adjustedAddress = inputAddress.toLowerCase()
//        println(adjustedAddress)

        val adjustedCity = inputCity.replace(" ", "%20").toLowerCase()
//        println(adjustedCity)

        AddressLiveData = addressRepository.searchAddress(state = inputState, city = adjustedCity, street = adjustedAddress)

        println("RESPOSTA API")
        println(AddressLiveData.value)
        return AddressLiveData
    }

}

// TODO: 06/04/2021 create enum for states
// TODO: 10/04/2021 link enum to spinner array
// TODO: 06/04/2021 adjust result fun to loop through array