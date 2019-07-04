package com.example.quizzz.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzz.R
import com.example.quizzz.entidades.Ranking
import com.example.quizzz.entidades.RankingResponse
import com.example.quizzz.servicos.RankingService
import com.example.quizzz.ui.RankingAdapter
import kotlinx.android.synthetic.main.activity_config.*
import kotlinx.android.synthetic.main.activity_ranking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RankingActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: RankingService
    lateinit var adapter: RankingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        configuraRetrofit()
        carregarLista()
    }

    fun configuraRetrofit() {

        //https://tads2019-todo-list.herokuapp.com/ranking
        retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(RankingService::class.java)
    }

    fun carregarLista() {
        service.getRanking().enqueue(object: Callback<RankingResponse> {
            override fun onFailure(call: Call<RankingResponse>, t: Throwable) {}

            override fun onResponse(call: Call<RankingResponse>, response: Response<RankingResponse>) {
                val ranking = response.body()?.ranking
                if (ranking != null) {
                    configuraRecyclerView(ranking)
                }
            }
        })
    }

    fun configuraRecyclerView(ranking: List<Ranking>) {
        adapter = RankingAdapter(ranking)
        listaRanking.adapter = adapter
        listaRanking.layoutManager = LinearLayoutManager(this@RankingActivity, RecyclerView.VERTICAL, false)
    }
}
