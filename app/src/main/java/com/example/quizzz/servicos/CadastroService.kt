package com.example.quizzz.servicos

import com.example.quizzz.entidades.Cadastro
import com.example.quizzz.entidades.CadastroResponse
import retrofit2.Call
import retrofit2.http.*

interface CadastroService {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("registrar")
    fun getCadastro(

        @Field("nome")
        nome: String,

        @Field("email")
        email: String,

        @Field("senha")
        senha: String

    ): Call<CadastroResponse>
}