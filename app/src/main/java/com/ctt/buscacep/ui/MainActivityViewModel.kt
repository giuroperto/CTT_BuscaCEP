package com.ctt.buscacep.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctt.buscacep.model.CEP

class MainActivityViewModel : ViewModel() {

//    dado mutavel do tipo CEP -> dado vivo que activity fica escutando e sabe quando algo mudar
    private lateinit var CEPLiveData : MutableLiveData<CEP>

//    pode ter valores de erro e excecoes
    fun buscarCEP(cepInserido: String) : MutableLiveData<CEP> {
        return CEPLiveData
    }
}