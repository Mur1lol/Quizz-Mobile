package com.example.quizzz.servicos

import com.example.quizzz.entidades.RankingResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers

interface RankingService {
    @Headers("Accept: application/json")

    @GET("ranking")
    fun getRanking(): Call<RankingResponse>
}