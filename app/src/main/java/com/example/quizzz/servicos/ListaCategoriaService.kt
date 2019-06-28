package com.example.quizzz.servicos

import com.example.quizzz.entidades.CadastroResponse
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.entidades.CategoriaListaResponse
import retrofit2.Call
import retrofit2.http.*

interface ListaCategoriaService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @GET("api_category")
    fun getListaCategoria(

        @Field("trivia_categories")
        list: List<Categoria>

    ): Call<CategoriaListaResponse>
}