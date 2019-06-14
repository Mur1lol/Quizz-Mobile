package com.example.quizzz.servicos

import com.example.quizzz.entidades.Resultado
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST
    fun postLogin(

        @Query("email")
        email: String,

        @Query("senha")
        senha: String

    ): Call<Resultado>
}