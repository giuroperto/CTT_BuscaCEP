package com.ctt.buscacep.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ctt.buscacep.model.CEP
import com.ctt.buscacep.repository.CEPRepository

// RESPONSAVEL PELA PARTE DA REGRA DE NEGOCIO QUE CONECTA A INTERACAO DO USUARIO COM A CAMADA DE COMUNICACAO (REPOSITORIO)
// RETORNANDO UM VALOR PARA A ACTIVITY

class MainActivityViewModel(
        private val cepRepository: CEPRepository = CEPRepository()
) : ViewModel() {

//    dado mutavel do tipo CEP -> dado vivo que activity fica escutando e sabe quando algo mudar
    private lateinit var CEPLiveData : MutableLiveData<CEP>

//    pode ter varios tipos de valores -> valores de erro e excecoes
    fun buscarCEP(cepInserido: String) : MutableLiveData<CEP> {

//    if (CEPLiveData == null && cepInserido.isNotEmpty()) {
//     se for vazio ja vai informar o usuario -> validacao de campos vai direto na activity
    if (CEPLiveData == null) {
//        se a requisicao ainda nao foi feita -> faz agora
        CEPLiveData = cepRepository.buscarViaCEP(cepUsuario = cepInserido)
    }
        return CEPLiveData
    }
}

// arquitetura MVVM faz parte do CLEAN ARCHITECTURE
// eh a parte da camada de apresentacao, que o usuario tem acesso
// camada de de dominio -> responsavel por preparar os dados para acessar o repositorio
//repositorio eh o responsavel por fazer uma chamada em um bd local, remoto... -> requisicao
// cada um tem seu papel/funcao
// REPOSITORIO EH QUEM FAZ A CHAMADA, que ate entao estava na activity
// camada de repositorio faz a chamada/requisicao