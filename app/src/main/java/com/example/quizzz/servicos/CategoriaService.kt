package com.example.quizzz.servicos

import com.example.quizzz.entidades.CategoriaResponse
import com.example.quizzz.entidades.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface CategoriaService {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @GET("api_category")
    fun getCategoria(

        @Field("id")
        id: Int,

        @Field("name")
        nome: String

    ): Call<CategoriaResponse>
}