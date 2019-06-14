package com.example.quizzz.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import com.example.quizzz.entidades.Resultado
import com.example.quizzz.servicos.LoginService
import com.example.quizzz.servicos.PerguntasService
import com.example.quizzz.ui.PerguntasAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.Retrofit.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: LoginService
    lateinit var adapter: PerguntasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //LOGIN
        //https://tads2019-todo-list.herokuapp.com/usuario/login



        configuraRetrofit()
        carregaDados()



    }

    fun configuraRetrofit() {
        retrofit = Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/usuario/login")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(LoginService::class.java)
    }

    fun carregaDados() {
        val email = txtEmailLogin.text.toString()
        val senha = txtSenhaLogin.text.toString()

        service.postLogin(email, senha).enqueue(object : Callback<Resultado> {
            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("TESTE", "123")
            }

            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                Log.d("HAHAHA", "123")
            }
        })
                                                                        
    }

}
