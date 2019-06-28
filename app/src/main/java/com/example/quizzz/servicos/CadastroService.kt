package com.example.quizzz.servicos

import com.example.quizzz.entidades.Cadastro
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface CadastroService {
    @POST("registrar")
    fun getCadastro(

        @Query("nome")
        nome: String,

        @Query("email")
        email: String,

        @Query("senha")
        senha: String

    ): Call<Cadastro>
}