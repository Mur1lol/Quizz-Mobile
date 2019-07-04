package com.example.quizzz.app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Categoria
import com.example.quizzz.entidades.Pergunta
import com.example.quizzz.entidades.Resultado
import com.example.quizzz.servicos.PerguntasService
import com.example.quizzz.ui.CategoriaListListener
import com.example.quizzz.ui.PerguntaListListener
import com.example.quizzz.ui.PerguntasAdapter
import kotlinx.android.synthetic.main.lista_activity.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ListaActivity : AppCompatActivity(), PerguntaListListener {
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
        var prefsDificuldade = getSharedPreferences("dificuldade", Context.MODE_PRIVATE)
        var prefsCategoria = getSharedPreferences("categoria", Context.MODE_PRIVATE)

        var dificuldade = prefsDificuldade.getString("dificuldade", null)
        var categoria = prefsCategoria.getInt("categoria", 0)


        service.getPerguntas(1, categoria , dificuldade).enqueue(object : Callback<Resultado> {
            override fun onFailure(call: Call<Resultado>, t: Throwable) {}

            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val perguntas = response.body()?.results
                if (perguntas != null)
                    configuraRecyclerView(perguntas)
            }
        })

    }

    fun configuraRecyclerView(perguntas: List<Pergunta>) {
        adapter = PerguntasAdapter(perguntas, this)
        listPerguntas.adapter = adapter
        listPerguntas.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    @SuppressLint("RestrictedApi")
    override fun abrirPerguntaActivity(pontos: Int) {
        btProximo.visibility = View.VISIBLE
        btProximo.setOnClickListener {
            val intent = Intent(this, PerguntaActivity::class.java)
            val bundle = Bundle()

            bundle.putInt("ponto", pontos)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}
