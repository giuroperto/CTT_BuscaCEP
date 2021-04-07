package com.ctt.buscacep.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.model.StateResponse
import com.ctt.buscacep.repository.CEPRepository

class CEPViewModel(
        private val cepRepository: CEPRepository = CEPRepository()
) : ViewModel() {

    private lateinit var CEPLiveData : MutableLiveData<StateResponse<CEP>>

    fun buscarCEP(cepInserido: String) : MutableLiveData<StateResponse<CEP>> {

        CEPLiveData = cepRepository.buscarViaCEP(cepUsuario = cepInserido)
        return CEPLiveData
    }
}
