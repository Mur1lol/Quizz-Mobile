package com.example.quizzz.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import com.example.quizzz.entidades.Login
import com.example.quizzz.servicos.LoginService
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://tads2019-todo-list.herokuapp.com/usuario/login

        configuraRetrofit()

        btLogar.setOnClickListener {
            carregaDados()
        }

        btRegistrar.setOnClickListener {
            abrirCadastroActivity()
        }
    }

    private fun abrirCadastroActivity() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun configuraRetrofit() {
        retrofit = Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/usuario/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        service = retrofit.create(LoginService::class.java)
    }

    fun carregaDados() {
        val email = txtEmailLogin.text.toString()
        val senha = txtSenhaLogin.text.toString()

        service.getLogin(email, senha).enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Mensagem de erro!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                Log.e("HAHAHA", "123")
                Toast.makeText(this@MainActivity, "Mensagem de sucesso!", Toast.LENGTH_SHORT).show()
                //Fazer condição se o email for valido
            }
        })
    }
}
