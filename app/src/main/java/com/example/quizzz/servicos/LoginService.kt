package com.example.quizzz.servicos

import com.example.quizzz.entidades.Login
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {
    @POST ("login")
    fun getLogin(

        @Query("email")
        email: String,

        @Query("senha")
        senha: String

    ): Call<Login>
}