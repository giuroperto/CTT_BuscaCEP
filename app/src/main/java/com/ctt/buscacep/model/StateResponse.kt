package com.ctt.buscacep.model

//serve para qualquer chamada de qualquer classe -> generico -> t

//     classe encarregada de trazer os status da requisicao

sealed class StateResponse<T>

class StateSuccess<T> (val data: T) : StateResponse<T>()
class StateError<T> (val error: Throwable) : StateResponse<T>()