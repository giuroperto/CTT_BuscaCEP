package com.ctt.buscacep

import com.ctt.buscacep.model.StateSuccess
import com.ctt.buscacep.ui.CEPViewModel
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class CEPViewModelTest {

    private val viewModel = CEPViewModel()

    @Test
    fun whenExecuteWithSuccessShouldReturnSuccess() {
        val liveData = viewModel.buscarCEP("04005003")

        val a = liveData as StateSuccess<*>
        a.data


        assertThat(liveData.value, instanceOf(StateSuccess::class.java))
    }
}