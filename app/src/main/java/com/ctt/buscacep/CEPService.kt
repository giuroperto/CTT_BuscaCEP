package com.ctt.buscacep

import com.ctt.buscacep.model.CEP
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


// responsavel por montar a URL final
// ex. apiexemplo.com/v1/ eh a minha base url
// para eu acessar uma lista de usuarios eh a url apiexemplo.com/v1/getUsuarios
// entao o service vai juntar o getUsuario (final da url) com a minha base
interface CEPService {

//     indicar tipo de requisicao
//    pegar -> GET
//    mandar -> POST
//    requisicoes http

//    indicar o caminho
//    mas tem uma parte do caminho que eh variavel
//    @GET("04003006/json/")
    @GET("{cepInserido}/json/")
    fun buscarCEP(
//    indica aqui que eh um caminho do get ali em cima
    @Path("cepInserido")
        cep: String
    ) : Call<CEP>
//    se fosse lista de cep seria Call<List<CEP>>
//    ) : Call<CEP>
//    ) : CEP
//    nao retorna CEP -> retorna chamada JSON
//    assim dizemos que eh uma chamada JSON que vamos depois converter para CEP

//    fun registrarCEP()
}