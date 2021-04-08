package com.ctt.buscacep.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctt.buscacep.model.Address
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.repository.AddressRepository

class AddressViewModel(
        private val addressRepository: AddressRepository = AddressRepository()
) : ViewModel() {
    private lateinit var AddressLiveData: MutableLiveData<StateResponse<Address>>

    fun fetchAddress(inputAddress: String, inputCity: String, inputState: String) : MutableLiveData<StateResponse<Address>> {
        AddressLiveData = addressRepository.searchAddress(state = inputState, city = inputCity, street = inputAddress)
        return AddressLiveData
    }
}

// TODO: 06/04/2021 create enum for states
// TODO: 06/04/2021 create fun to adjust city name when it has spaces
// TODO: 06/04/2021 adjust result fun to loop through array