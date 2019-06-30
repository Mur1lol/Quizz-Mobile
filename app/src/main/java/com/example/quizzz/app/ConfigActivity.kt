package com.example.quizzz.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.entidades.CategoriaResponse
import com.example.quizzz.servicos.ListaCategoriaService
import com.example.quizzz.ui.CategoriasAdapter
import kotlinx.android.synthetic.main.activity_config.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context


class ConfigActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var retrofit: Retrofit
    lateinit var service: ListaCategoriaService
    //lateinit var adapter: CategoriasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        configuraRetrofit()
        carregarLista()

        idCategoria.onItemSelectedListener = this

        btJogar.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.e("Selecionado :", "N SEI")
    }

    fun configuraRetrofit() {

        //https://opentdb.com/api_category.php
        retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(ListaCategoriaService::class.java)
    }

    fun carregarLista() {
        service.getListaCategoria().enqueue(object: Callback<CategoriaResponse>{
            override fun onFailure(call: Call<CategoriaResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<CategoriaResponse>, response: Response<CategoriaResponse>) {
//                val categorias = response.body()?.trivia_categories
//                if (categorias != null) {
//                    configuraRecyclerView(categorias)
//                }
                if (response.body()!= null) {
                    var categorias: CategoriaResponse = response.body()!!

                    val adapter : ArrayAdapter<Categoria> = ArrayAdapter(this@ConfigActivity, android.R.layout.simple_spinner_item, categorias.trivia_categories)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    idCategoria.adapter = adapter




                    Log.e("CATEGORIAS: ",""+categorias.trivia_categories)
                }
            }

        })
    }

//    fun configuraRecyclerView(categorias: List<Categoria>) {
//        adapter = CategoriasAdapter(categorias)
//
//        categorias.adapter = adapter
//        categorias.layoutManager = LinearLayoutManager(this@ConfigActivity, RecyclerView.VERTICAL, false)
//    }

}
