package com.example.quizzz.servicos

import com.example.quizzz.entidades.Resultado
import retrofit2.Call
import retrofit2.http.*


interface PerguntasService {
    @GET("api.php")
    fun getPerguntas(

        @Query("amount")
        qtde: Int,

        @Query("category")
        categoria: Int?,

        @Query("difficulty")
        dificuldade: String?

    ): Call<Resultado>

}