package com.example.quizzz.app

import android.content.Intent
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

//        val prefs = getPreferences(0)

//        val email1 = prefs.edit().putString("user_email", txtEmailLogin.text.toString()).commit()
//        val senha1 = prefs.edit().putString("user_senha", txtSenhaLogin.text.toString()).commit()
//
//        Log.e("TESTE", ""+email1+senha1)


//        val email = prefs.getString("user_email", null)
//        val senha = prefs.getString("user_password", null)





        configuraRetrofit()

        btLogar.setOnClickListener {
                carregaDados(txtEmailLogin.text.toString(), txtSenhaLogin.text.toString())
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
                    if (senha.length>=6) {
                        if (login.sucesso) {
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

    private fun abrirConfigActivity() {
        val intent = Intent(this, ConfigActivity::class.java)
        startActivity(intent)
    }
}
