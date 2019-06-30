package com.example.quizzz.servicos

import com.example.quizzz.entidades.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST ("usuario/login")
    fun getLogin(

        @Field("email")
        email: String,

        @Field("senha")
        senha: String

    ): Call<LoginResponse>
}