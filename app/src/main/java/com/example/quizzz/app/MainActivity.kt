package com.example.quizzz.app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzz.R
import com.example.quizzz.entidades.LoginResponse
import com.example.quizzz.servicos.LoginService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var service: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://tads2019-todo-list.herokuapp.com/usuario/login

        configuraRetrofit()

        //Entrar Sozinho
        var prefsUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        //txtEmailLogin.setText(prefs.getString("email", null))
        //txtSenhaLogin.setText(prefs.getString("senha", null))

        var email = prefsUsuario.getString("email", null)
        var senha = prefsUsuario.getString("senha", null)

        if(email != null && senha != null) {
            carregaDados(email, senha)
        }

        btLogar.setOnClickListener {
                carregaDados(txtEmailLogin.text.toString(), txtSenhaLogin.text.toString())
        }

        btRegistrar.setOnClickListener {
            abrirCadastroActivity()
        }
    }

    fun configuraRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://tads2019-todo-list.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(LoginService::class.java)
    }

    fun carregaDados(email: String, senha: String) {
        service.getLogin(email, senha).enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Mensagem de erro!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                var login: LoginResponse = response.body()!!
                if(response.body()!=null){
                    if (email != null && senha.length>=6) {
                        if (login.sucesso) {
                            var prefsUsuario = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                            var edUser = prefsUsuario.edit()

                            edUser.putString("email", txtEmailLogin.text.toString())
                            edUser.putString("senha", txtSenhaLogin.text.toString())
                            edUser.apply()

                            Toast.makeText(this@MainActivity, login.mensagem, Toast.LENGTH_SHORT).show()
                            abrirConfigActivity()
                        } else {
                            Toast.makeText(this@MainActivity, login.mensagem, Toast.LENGTH_SHORT).show()
                        }
                    }
                    else {
                        Toast.makeText(this@MainActivity, login.mensagem, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun abrirCadastroActivity() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    private fun abrirConfigActivity() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }
}
