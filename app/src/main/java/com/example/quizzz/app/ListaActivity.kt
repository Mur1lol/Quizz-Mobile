package com.example.quizzz.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Pergunta
import com.example.quizzz.entidades.Resultado
import com.example.quizzz.servicos.PerguntasService
import com.example.quizzz.ui.PerguntasAdapter
import kotlinx.android.synthetic.main.lista_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ListaActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: PerguntasService
    lateinit var adapter: PerguntasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista_activity)

        configuraRetrofit()
        carregaDados()

        //https://opentdb.com/api.php?amount=10&category=21&difficulty=hard
    }

    fun configuraRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(PerguntasService::class.java)
    }

    fun carregaDados() {
        service.getPerguntas(1, null, null).enqueue(object : Callback<Resultado> {
            override fun onFailure(call: Call<Resultado>, t: Throwable) {

            }

            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val perguntas = response.body()?.results
                if (perguntas != null)
                    configuraRecyclerView(perguntas)
            }
        })

    }

    fun configuraRecyclerView(perguntas: List<Pergunta>) {
        adapter = PerguntasAdapter(perguntas)
        listPerguntas.adapter = adapter
        listPerguntas.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

}
