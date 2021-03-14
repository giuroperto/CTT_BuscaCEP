package com.ctt.buscacep

import com.ctt.buscacep.model.StateSuccess
import com.ctt.buscacep.ui.MainActivityViewModel
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class MainActivityViewModelTest {

    private val viewModel = MainActivityViewModel()

    @Test
    fun whenExecuteWithSuccessShouldReturnSuccess() {
        val liveData = viewModel.buscarCEP("04005003")

//        instancia da classe
//        retorna um generico -> como trabalhar com acesso a data

//        mas so chega a isso apos uma serie de validacoes e se certifica que nao tem como vir nulo ou vir de outra maneira
        val a = liveData as StateSuccess<*>
        a.data


        assertThat(liveData.value, instanceOf(StateSuccess::class.java))

//         a view model permite testar as requisicoes e validar o retorno delas 
    }
}