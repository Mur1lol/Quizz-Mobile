package com.example.quizzz.servicos

import com.example.quizzz.entidades.LoginResponse
import com.example.quizzz.entidades.Resultado
import retrofit2.Call
import retrofit2.http.*

interface PontuacaoService {
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @PUT("pontuacao")
    fun putPontos(

        @Field("email")
        email: String,

        @Field("senha")
        senha: String,

        @Field("pontos")
        pontos: Int

    ): Call<LoginResponse>
}