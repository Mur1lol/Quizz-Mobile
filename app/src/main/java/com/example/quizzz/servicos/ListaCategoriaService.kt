package com.example.quizzz.servicos

import com.example.quizzz.entidades.CategoriaResponse
import retrofit2.Call
import retrofit2.http.*

interface ListaCategoriaService {

    @GET("api_category.php")
    fun getListaCategoria(): Call<CategoriaResponse>
}